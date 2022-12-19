package com.viking_bank.cqrs.core.producers;

import com.viking_bank.cqrs.core.events.BaseEvent;

public interface EventProducer {

    void produce(String topic, BaseEvent event);
}
