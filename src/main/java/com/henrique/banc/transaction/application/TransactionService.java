package com.henrique.banc.transaction.application;

import com.henrique.banc.authorization.domain.AuthorizerService;
import com.henrique.banc.notification.domain.NotificationService;
import com.henrique.banc.shared.utils.exceptions.InvalidTransactionException;
import com.henrique.banc.transaction.domain.Transaction;
import com.henrique.banc.transaction.infrastructure.TransactionRepository;
import com.henrique.banc.wallet.domain.Wallet;
import com.henrique.banc.wallet.domain.WalletType;
import com.henrique.banc.wallet.infrastructure.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    @Transactional
    public Transaction create(Transaction transaction) {
        validate(transaction);

        var newTransaction = transactionRepository.save(transaction);

        var walletPayer = walletRepository.findById(transaction.payer()).get();
        var walletPayee = walletRepository.findById(transaction.payee()).get();
        walletRepository.save(walletPayer.debit(transaction.value()));
        walletRepository.save(walletPayee.credit(transaction.value()));

        authorizerService.authorize(transaction);

        notificationService.notify(newTransaction);

        return newTransaction;
    }

    private void validate(Transaction transaction) {
        walletRepository.findById(transaction.payee()).map(payee -> walletRepository.findById(transaction.payer())
                .map(payer -> isTransactionValid(transaction, payer) ? transaction : null).orElseThrow(() -> new
                        InvalidTransactionException("Invalid Transaction - %s".formatted(transaction)))).orElseThrow(() -> new
                InvalidTransactionException("Invalid Transaction - %s".formatted(transaction)));
    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM.getValue() && payer.balance().compareTo(transaction.value()) >= 0 &&
                payer.balance().compareTo(transaction.value()) >= 0 &&
                !payer.id().equals(transaction.payee());
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
