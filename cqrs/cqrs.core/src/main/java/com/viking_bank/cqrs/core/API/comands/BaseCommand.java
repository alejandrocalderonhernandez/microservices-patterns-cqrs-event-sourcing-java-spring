package com.viking_bank.cqrs.core.API.comands;

import com.viking_bank.cqrs.core.infrastructure.events.messages.Messages;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class BaseCommand extends Messages {

    public BaseCommand(String id) {
        super(id);
    }
}
