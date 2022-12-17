package com.viking_bank.account.cmd.domain;

import com.viking_bank.cqrs.core.events.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventStoreRepository extends MongoRepository<EventModel, String> {

    List<EventModel> findByAggregateIdentified(String aggregateIdentified);
}
