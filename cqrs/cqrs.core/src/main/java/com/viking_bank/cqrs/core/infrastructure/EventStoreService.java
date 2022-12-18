package com.viking_bank.cqrs.core.infrastructure;

import com.viking_bank.cqrs.core.events.BaseEvent;

import java.util.Collection;
import java.util.Set;

public interface EventStoreService {
    void save(String aggregateId, Collection<BaseEvent> newEvents, Integer newVersion);

    Set<BaseEvent> findEventsByAggregateId(String aggregateId);
}
