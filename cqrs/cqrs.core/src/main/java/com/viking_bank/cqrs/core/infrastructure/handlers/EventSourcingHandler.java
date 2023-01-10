package com.viking_bank.cqrs.core.infrastructure.handlers;

import com.viking_bank.cqrs.core.domain.aggregates.AggregateRoot;

public interface EventSourcingHandler<T> {

    void saveState(AggregateRoot aggregateRoot);
    T getById(String id);
}
