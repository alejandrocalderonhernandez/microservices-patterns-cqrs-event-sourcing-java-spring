package com.viking_bank.account.cmd.domain.repositories;

import com.viking_bank.cqrs.core.events.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.HashSet;

public interface EventStoreRepository extends MongoRepository<EventModel, String> {

    HashSet<EventModel> findByAggregateIdentified(String aggregateIdentified);
}
