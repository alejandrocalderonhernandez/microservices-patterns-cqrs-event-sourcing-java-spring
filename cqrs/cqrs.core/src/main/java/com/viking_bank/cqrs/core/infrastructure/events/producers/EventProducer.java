package com.viking_bank.cqrs.core.infrastructure.events.producers;

import com.viking_bank.cqrs.core.infrastructure.events.BaseEvent;

public interface EventProducer {

    void produce(String topic, BaseEvent event);
}
