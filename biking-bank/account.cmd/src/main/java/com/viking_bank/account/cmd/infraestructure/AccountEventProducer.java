package com.viking_bank.account.cmd.infraestructure;

import com.viking_bank.cqrs.core.events.BaseEvent;
import com.viking_bank.cqrs.core.producers.EventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountEventProducer implements EventProducer{

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public AccountEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void produce(String topic, BaseEvent message) {
        try {
            this.kafkaTemplate.send(topic, message);
        } catch (KafkaException e) {
            log.error(e.getLocalizedMessage());
        }
    }

}
