package com.viking_bank.account.query.API.controllers;

import com.viking_bank.account.common.dto.BaseResponse;
import com.viking_bank.account.query.API.models.AccountLookupResponse;
import com.viking_bank.account.query.domain.BankAccount;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountByHolderQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountByIdQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountWithBalanceQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAllAccountsQueryRequest;
import com.viking_bank.cqrs.core.domain.entities.EqualityType;
import com.viking_bank.cqrs.core.infrastructure.dispatchers.QueryDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = RouterConstants.account_lookup_path)
public class AccountLookupController {

    private final QueryDispatcher queryDispatcher;

    public AccountLookupController(QueryDispatcher queryDispatcher) {
        this.queryDispatcher = queryDispatcher;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllAccounts() {
        List<BankAccount> accounts = queryDispatcher.send(new FindAllAccountsQueryRequest())
                .stream()
                .map(bE -> (BankAccount) bE)
                .collect(Collectors.toList());

        if (accounts.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(AccountLookupResponse
                .builder()
                .accounts(accounts)
                .message("Success")
                .build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable String id) {
        List<BankAccount> accounts = queryDispatcher.send(new FindAccountByIdQueryRequest(id))
                .stream()
                .map(bE -> (BankAccount) bE)
                .collect(Collectors.toList());

        if (accounts.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(AccountLookupResponse
                .builder()
                .accounts(accounts)
                .message("Success")
                .build());
    }

    @GetMapping(path = "/{holder}")
    public ResponseEntity<BaseResponse> getByHolder(@PathVariable String holder) {
        List<BankAccount> accounts = queryDispatcher.send(new FindAccountByHolderQueryRequest(holder))
                .stream()
                .map(bE -> (BankAccount) bE)
                .collect(Collectors.toList());

        if (accounts.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(AccountLookupResponse
                .builder()
                .accounts(accounts)
                .message("Success")
                .build());
    }

    @GetMapping(path = "/with-balance")
    public ResponseEntity<BaseResponse> getByHolder(
            @RequestParam String equalityType,
            @RequestParam Double balance
    ) {
        List<BankAccount> accounts = queryDispatcher.send(new FindAccountWithBalanceQueryRequest(balance, EqualityType.valueOf(equalityType)))
                .stream()
                .map(bE -> (BankAccount) bE)
                .collect(Collectors.toList());

        if (accounts.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(AccountLookupResponse
                .builder()
                .accounts(accounts)
                .message("Success")
                .build());
    }
}
