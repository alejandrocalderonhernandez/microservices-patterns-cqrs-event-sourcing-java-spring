package com.viking_bank.account.query.domain;

import com.viking_bank.cqrs.core.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
    Optional<BankAccount> findByAccountHolder(String accountHolder);

    Set<BankAccount> findByBalanceGreaterThan(Double balance);

    Set<BaseEntity> findByBalanceLessThan(Double balance);
}
