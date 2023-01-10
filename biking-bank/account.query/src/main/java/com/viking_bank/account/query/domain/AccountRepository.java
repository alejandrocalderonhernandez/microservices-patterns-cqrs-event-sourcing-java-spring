package com.viking_bank.account.query.domain;

import com.viking_bank.cqrs.core.domain.entities.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
    Optional<BankAccount> findByAccountHolder(String accountHolder);

    List<BankAccount> findByBalanceGreaterThan(Double balance);

    List<BankAccount> findByBalanceLessThan(Double balance);
}
