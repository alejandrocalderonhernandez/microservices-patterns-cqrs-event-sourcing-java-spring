package com.viking_bank.account.cmd.service;

import com.google.common.collect.Iterables;
import com.viking_bank.account.cmd.domain.AccountAggregate;
import com.viking_bank.account.cmd.domain.EventStoreRepository;
import com.viking_bank.cqrs.core.events.BaseEvent;
import com.viking_bank.cqrs.core.events.EventModel;
import com.viking_bank.cqrs.core.infrastructure.EventStoreService;
import com.viking_bank.cqrs.core.producers.EventProducer;
import com.viking_bank.cqrs.core.util.IllegalEventVersionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountEventStore implements EventStoreService {
    private final EventStoreRepository eventStoreRepository;
    private final EventProducer eventProducer;

    public AccountEventStore(EventStoreRepository eventStoreRepository, EventProducer eventProducer) {
        this.eventStoreRepository = eventStoreRepository;
        this.eventProducer = eventProducer;
    }

    @Override
    public void save(String aggregateId, Collection<BaseEvent> newEvents, Integer newVersion) {
        var eventsResponse = this.eventStoreRepository.findByAggregateIdentified(aggregateId);

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
            if (Objects.nonNull(persistedEvent.getId())) {
                this.eventProducer.produce(e.getClass().getSimpleName(), e);
            } else {
                log.error("Error on save event");
            }
        });
    }

    @Override
    public Set<BaseEvent> findEventsByAggregateId(String aggregateId) {
        var eventsResponse = eventStoreRepository.findByAggregateIdentified(aggregateId);

        if (eventsResponse.isEmpty())
            throw new IllegalArgumentException("Not exist records with aggregateId" + aggregateId);

        return eventsResponse
                .stream()
                .map(EventModel::getEventData)
                .collect(Collectors.toUnmodifiableSet());
    }
}
