package com.viking_bank.account.cmd.infraestructure.handlers;

import com.viking_bank.account.cmd.domain.aggregates.AccountAggregate;
import com.viking_bank.cqrs.core.domain.aggregates.AggregateRoot;
import com.viking_bank.cqrs.core.infrastructure.events.BaseEvent;
import com.viking_bank.cqrs.core.infrastructure.handlers.EventSourcingHandler;
import com.viking_bank.cqrs.core.infrastructure.services.EventStoreService;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {
    private final EventStoreService eventStoreService;

    public AccountEventSourcingHandler(EventStoreService eventStoreService) {
        this.eventStoreService = eventStoreService;
    }

    @Override
    public void saveState(AggregateRoot aggregateRoot) {
        this.eventStoreService
                .save(aggregateRoot.getId(), aggregateRoot.getUncommittedChanges(), aggregateRoot.getVersion());
        aggregateRoot.markChangesAsCommit();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate = new AccountAggregate();
        var events = this.eventStoreService.findEventsByAggregateId(id);
        aggregate.replayEvent(events);
        var latestVersion = events
                .stream()
                .map(BaseEvent::getVersion)
                .max(Comparator.naturalOrder());

        aggregate.setVersion(latestVersion.orElseThrow(IllegalStateException::new));

        return  aggregate;
    }

}