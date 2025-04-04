package com.henrique.banc.transaction.infrastructure;

import com.henrique.banc.transaction.application.TransactionService;
import com.henrique.banc.transaction.domain.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.create(transaction);
    }

    @GetMapping
    public List<Transaction> listTransactions() {
        return transactionService.list();
    }
}