package com.viking_bank.account.cmd.infraestructure;

import com.viking_bank.account.cmd.domain.AccountAggregate;
import com.viking_bank.cqrs.core.domain.AggregateRoot;
import com.viking_bank.cqrs.core.handlers.EventSourcingHandler;

public class AccountEventSourcingHaldler implements EventSourcingHandler<AccountAggregate> {
    @Override
    public void saveState(AggregateRoot aggregateRoot) {

    }

    @Override
    public AccountAggregate getById(String id) {
        return null;
    }
}
