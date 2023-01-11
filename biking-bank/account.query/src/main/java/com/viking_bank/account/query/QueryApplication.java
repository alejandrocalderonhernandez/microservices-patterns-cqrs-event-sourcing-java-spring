package com.viking_bank.account.query;

import com.viking_bank.account.query.API.handlers.QueryHandler;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountByHolderQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountByIdQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAccountWithBalanceQueryRequest;
import com.viking_bank.cqrs.core.API.models.requests.FindAllAccountsQueryRequest;
import com.viking_bank.cqrs.core.infrastructure.dispatchers.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class QueryApplication {
    @Autowired
    private QueryDispatcher queryDispatcher;
    @Autowired
    private QueryHandler queryHandler;

    @PostConstruct
    public void registerHandlers() {
        queryDispatcher.registerHandler(FindAllAccountsQueryRequest.class, q -> this.queryHandler.handle((FindAllAccountsQueryRequest) q));
        queryDispatcher.registerHandler(FindAccountByIdQueryRequest.class, q -> this.queryHandler.handle((FindAccountByIdQueryRequest) q));
        queryDispatcher.registerHandler(FindAccountByHolderQueryRequest.class, q -> this.queryHandler.handle((FindAccountByHolderQueryRequest) q));
        queryDispatcher.registerHandler(FindAccountWithBalanceQueryRequest.class, q -> this.queryHandler.handle((FindAccountWithBalanceQueryRequest) q));
    }
    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class, args);
    }

}