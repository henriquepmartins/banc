package com.henrique.banc.transaction.infrastructure;

import com.henrique.banc.transaction.domain.Transaction;
import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {

}
