package com.henrique.banc.wallet.infrastructure;

import com.henrique.banc.wallet.domain.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
