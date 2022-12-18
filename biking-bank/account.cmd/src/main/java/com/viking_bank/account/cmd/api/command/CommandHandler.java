package com.viking_bank.account.cmd.api.command;

/**
 * This interface represents "abstract Colleague" in design patter mediator
 */
public interface CommandHandler {
    void handle(OpenAccountCommand command);
    void handle(DepositFoundsCommand command);
    void handle(WithdrawFoundsCommand command);
    void handle(CloseAccountCommand command);
}
