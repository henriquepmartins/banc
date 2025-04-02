package com.henrique.banc.transaction.application;

import com.henrique.banc.transaction.domain.Transaction;
import com.henrique.banc.transaction.infrastructure.TransactionRepository;
import com.henrique.banc.wallet.infrastructure.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public Transaction create(Transaction transaction) {
        var newTransaction = transactionRepository.save(transaction);

        
        return newTransaction;
    }
}
