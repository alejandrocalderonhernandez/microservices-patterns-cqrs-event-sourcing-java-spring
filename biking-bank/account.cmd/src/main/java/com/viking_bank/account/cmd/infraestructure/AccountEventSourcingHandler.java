package com.viking_bank.account.cmd.infraestructure;

import com.viking_bank.account.cmd.domain.AccountAggregate;
import com.viking_bank.cqrs.core.domain.AggregateRoot;
import com.viking_bank.cqrs.core.events.BaseEvent;
import com.viking_bank.cqrs.core.handlers.EventSourcingHandler;
import com.viking_bank.cqrs.core.infrastructure.EventStoreService;
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