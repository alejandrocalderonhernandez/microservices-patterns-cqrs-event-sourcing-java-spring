package com.viking_bank.cqrs.core.comands;

/**
 * This interface represents a "abstract Colleague" in design patter mediator
 */
@FunctionalInterface
public interface CommandHandlerMethod <T extends BaseCommand> {

    void handle(BaseCommand command);
}
