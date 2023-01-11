package com.viking_bank.account.query.API.handlers;

import com.viking_bank.account.query.domain.AccountRepository;
import com.viking_bank.account.query.domain.BankAccount;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountByHolderQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountByIdQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountWithBalanceQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAllAccountsQueryRequest;
import com.viking_bank.cqrs.core.domain.entities.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AccountQueryHandler implements QueryHandler {
    private final AccountRepository accountRepository;

    public AccountQueryHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<BaseEntity> handle(FindAllAccountsQueryRequest query) {
        final var accounts = this.accountRepository.findAll();
        List<BaseEntity> result = new ArrayList<>();
        accounts.forEach(result::add);
        return result;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQueryRequest query) {
        final var account = this.accountRepository.findById(query.getId());
        return List.of(account.orElse(new BankAccount()));
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQueryRequest query) {
        final var account = this.accountRepository.findByAccountHolder(query.getAccountHolder());
        return List.of(account.orElse(new BankAccount()));
    }

    @Override
    public List<BaseEntity> handle(FindAccountWithBalanceQueryRequest query) {
        List <BankAccount> accounts;
        switch (query.getType()) {
            case LESS_THAN:
                accounts = this.accountRepository.findByBalanceLessThan(query.getBalance()); break;
             case GREATER_THAN:
                 accounts = this.accountRepository.findByBalanceGreaterThan(query.getBalance()); break;
            default: throw new IllegalArgumentException("EqualityType invalid");
        }
        return new ArrayList<>(accounts);
    }
}
