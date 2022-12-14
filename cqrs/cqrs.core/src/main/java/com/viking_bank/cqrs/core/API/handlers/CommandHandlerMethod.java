package com.viking_bank.cqrs.core.API.handlers;

import com.viking_bank.cqrs.core.API.comands.BaseCommand;

/**
 * This interface represents a "abstract Colleague" in design patter mediator
 */
@FunctionalInterface
public interface CommandHandlerMethod <T extends BaseCommand> {

    void handle(BaseCommand command);
}
