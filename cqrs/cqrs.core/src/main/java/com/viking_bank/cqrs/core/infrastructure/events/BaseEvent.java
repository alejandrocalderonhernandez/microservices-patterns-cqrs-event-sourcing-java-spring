package com.viking_bank.cqrs.core.infrastructure.events;

import com.viking_bank.cqrs.core.infrastructure.events.messages.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseEvent extends Messages {

    private Integer version;
}
