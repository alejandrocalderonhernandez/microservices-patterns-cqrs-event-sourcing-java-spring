package com.viking_bank.cqrs.core.infrastructure;

import com.viking_bank.cqrs.core.events.BaseEvent;

import java.util.Collection;
import java.util.List;

public interface EventStoreService {

    void save(String aggregateId, Collection<BaseEvent> newEvents, Integer newVersion);

    List<BaseEvent> findEventsByAggregateId(String aggregateId);
}
