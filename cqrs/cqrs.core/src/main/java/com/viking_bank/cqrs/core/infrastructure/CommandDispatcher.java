package com.viking_bank.cqrs.core.infrastructure;

import com.viking_bank.cqrs.core.comands.BaseCommand;
import com.viking_bank.cqrs.core.comands.CommandHandlerMethod;

/**
* This interface represents "abstract Mediator" in design patter mediator
*/
public interface CommandDispatcher {

    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
