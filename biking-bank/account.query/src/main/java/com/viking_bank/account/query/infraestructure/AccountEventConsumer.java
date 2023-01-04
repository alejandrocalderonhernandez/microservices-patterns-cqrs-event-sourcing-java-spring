package com.viking_bank.account.query.infraestructure;

import com.viking_bank.account.common.events.AccountClosedEvent;
import com.viking_bank.account.common.events.AccountOpenedEvent;
import com.viking_bank.account.common.events.FoundDepositedEvent;
import com.viking_bank.account.common.events.FoundsWithdrawnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountEventConsumer implements EventConsumer {
    private final AccountService accountService;

    public AccountEventConsumer(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(AccountOpenedEvent event, Acknowledgment ack) {
        log.info("receiving data from kafka topic: AccountOpenedEvent, data: {}", event);
        ack.acknowledge();
        this.accountService.on(event);
    }

    @Override
    @KafkaListener(topics = "FoundDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(FoundDepositedEvent event, Acknowledgment ack) {
        log.info("receiving data from kafka topic: FoundDepositedEvent, data: {}", event);
        ack.acknowledge();
        this.accountService.on(event);
    }

    @Override
    @KafkaListener(topics = "FoundsWithdrawnEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(FoundsWithdrawnEvent event, Acknowledgment ack) {
        log.info("receiving data from kafka topic: FoundsWithdrawnEvent, data: {}", event);
        ack.acknowledge();
        this.accountService.on(event);
    }

    @Override
    @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(AccountClosedEvent event, Acknowledgment ack) {
        log.info("receiving data from kafka topic: AccountClosedEvent, data: {}", event);
        ack.acknowledge();
        this.accountService.on(event);
    }
}
