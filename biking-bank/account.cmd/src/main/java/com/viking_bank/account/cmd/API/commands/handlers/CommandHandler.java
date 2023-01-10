package com.viking_bank.account.cmd.API.commands.handlers;

import com.viking_bank.account.cmd.API.commands.CloseAccountCommand;
import com.viking_bank.account.cmd.API.commands.DepositFoundsCommand;
import com.viking_bank.account.cmd.API.commands.OpenAccountCommand;
import com.viking_bank.account.cmd.API.commands.WithdrawFoundsCommand;

/**
 * This interface represents "abstract Colleague" in design patter mediator
 */
public interface CommandHandler {
    void handle(OpenAccountCommand command);
    void handle(DepositFoundsCommand command);
    void handle(WithdrawFoundsCommand command);
    void handle(CloseAccountCommand command);
}
