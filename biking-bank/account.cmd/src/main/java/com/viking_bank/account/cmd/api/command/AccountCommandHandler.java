package com.viking_bank.account.cmd.api.command;

import com.viking_bank.account.cmd.domain.models.AccountAggregate;
import com.viking_bank.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandHandler implements CommandHandler {
    private final EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    public AccountCommandHandler(EventSourcingHandler<AccountAggregate> eventSourcingHandler) {
        this.eventSourcingHandler = eventSourcingHandler;
    }

    @Override
    public void handle(OpenAccountCommand command) {
        var aggregate = new AccountAggregate(command);
        this.eventSourcingHandler.saveState(aggregate);
    }

    @Override
    public void handle(DepositFoundsCommand command) {
        var aggregate = this.eventSourcingHandler.getById(command.getId());
        aggregate.depositFounds(command.getAmount());
        eventSourcingHandler.saveState(aggregate);
    }

    @Override
    public void handle(WithdrawFoundsCommand command) {
        var aggregate = this.eventSourcingHandler.getById(command.getId());
        if (command.getAmount() > command.getAmount())
            throw new IllegalStateException("Insufficient funds");
        aggregate.withdrawFounds(command.getAmount());
        eventSourcingHandler.saveState(aggregate);
    }

    @Override
    public void handle(CloseAccountCommand command) {
        var aggregate = this.eventSourcingHandler.getById(command.getId());
        aggregate.closeAccount();
        eventSourcingHandler.saveState(aggregate);
    }
}
