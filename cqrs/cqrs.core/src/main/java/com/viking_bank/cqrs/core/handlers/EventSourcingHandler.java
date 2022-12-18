package com.viking_bank.cqrs.core.handlers;

import com.viking_bank.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {

    void saveState(AggregateRoot aggregateRoot);
    T getById(String id);
}
