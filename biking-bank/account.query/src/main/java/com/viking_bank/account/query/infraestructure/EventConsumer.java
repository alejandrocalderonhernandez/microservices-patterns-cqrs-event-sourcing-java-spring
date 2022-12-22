package com.viking_bank.account.query.infraestructure;

import com.viking_bank.account.common.events.AccountClosedEvent;
import com.viking_bank.account.common.events.AccountOpenedEvent;
import com.viking_bank.account.common.events.FoundDepositedEvent;
import com.viking_bank.account.common.events.FoundsWithdrawnEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);

    void consume(@Payload FoundDepositedEvent event, Acknowledgment ack);

    void consume(@Payload FoundsWithdrawnEvent event, Acknowledgment ack);

    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
