package com.viking_bank.account.cmd.service;

import com.google.common.collect.Iterables;
import com.viking_bank.account.cmd.domain.AccountAggregate;
import com.viking_bank.account.cmd.domain.EventStoreRepository;
import com.viking_bank.cqrs.core.events.BaseEvent;
import com.viking_bank.cqrs.core.events.EventModel;
import com.viking_bank.cqrs.core.infrastructure.EventStoreService;
import com.viking_bank.cqrs.core.util.IllegalEventVersionException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AccountEventStore implements EventStoreService {

    private final EventStoreRepository eventStoreRepository;

    public AccountEventStore(EventStoreRepository eventStoreRepository) {
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public void save(String aggregateId, Collection<BaseEvent> newEvents, Integer newVersion) {
        var eventsResponse = this.eventStoreRepository.findByAggregateIdentified(aggregateId);

        if (eventsResponse.isEmpty())
                throw new IllegalArgumentException("Not exist records with aggregateId" + aggregateId);

        if (newVersion != -1 && !Objects.equals(Iterables.getLast(eventsResponse).getVersion(), newVersion))
            throw new IllegalEventVersionException(newVersion);

        AtomicInteger updatedVersion = new AtomicInteger(-1);

        newEvents.forEach(e -> {
            updatedVersion.getAndIncrement();
            e.setVersion(updatedVersion.get());
            var eventModel = EventModel
                    .builder()
                    .aggregateIdentified(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(updatedVersion.get())
                    .eventType(e.getClass().getTypeName())
                    .eventData(e)
                    .build();

            var persistedEvent = this.eventStoreRepository.save(eventModel);
            // TO DO implement kafka
        });


    }

    @Override
    public List<BaseEvent> findEventsByAggregateId(String aggregateId) {
        var eventsResponse = eventStoreRepository.findByAggregateIdentified(aggregateId);

        if (eventsResponse.isEmpty())
            throw new IllegalArgumentException("Not exist records with aggregateId" + aggregateId);

        return eventsResponse
                .stream()
                .map(EventModel::getEventData)
                .collect(Collectors.toUnmodifiableList());
    }
}
