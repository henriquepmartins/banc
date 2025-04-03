package com.henrique.banc.transaction.application;

import com.henrique.banc.shared.exceptions.InvalidTransactionException;
import com.henrique.banc.transaction.domain.Transaction;
import com.henrique.banc.transaction.infrastructure.TransactionRepository;
import com.henrique.banc.wallet.domain.Wallet;
import com.henrique.banc.wallet.domain.WalletType;
import com.henrique.banc.wallet.infrastructure.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public Transaction create(Transaction transaction) {
        validate(transaction);

        var newTransaction = transactionRepository.save(transaction);

        var wallet = walletRepository.findById(transaction.payer()).get();
        walletRepository.save(wallet.debit(transaction.value()));

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
}
