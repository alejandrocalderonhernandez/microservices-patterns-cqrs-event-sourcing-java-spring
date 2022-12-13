package com.viking_bank.cqrs.core.comands;

import com.viking_bank.cqrs.core.messages.Messages;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class BaseCommand extends Messages {

    public BaseCommand(String id) {
        super(id);
    }
}
