package com.viking_bank.account.query.API.handlers;

import com.viking_bank.cqrs.core.API.models.requests.FindAccountByHolderQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountByIdQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountWithBalanceQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAllAccountsQueryRequest;
import com.viking_bank.cqrs.core.domain.entities.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllAccountsQueryRequest query);

    List<BaseEntity> handle(FindAccountByIdQueryRequest query);

    List<BaseEntity> handle(FindAccountByHolderQueryRequest query);

    List<BaseEntity> handle(FindAccountWithBalanceQueryRequest query);
}
