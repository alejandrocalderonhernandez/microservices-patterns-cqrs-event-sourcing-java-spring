package com.viking_bank.account.cmd.domain;

import com.viking_bank.account.cmd.api.command.OpenAccountCommand;
import com.viking_bank.account.common.events.AccountClosedEvent;
import com.viking_bank.account.common.events.AccountOpenedEvent;
import com.viking_bank.account.common.events.FoundDepositedEvent;
import com.viking_bank.account.common.events.FoundsWithdrawnEvent;
import com.viking_bank.cqrs.core.domain.AggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    private boolean active;
    @Getter
    private Double balance;

    /**
     * @param command received by te client to build event to execute
     */
    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent
                    .builder()
                    .id(command.getId())
                    .accountHolder(command.getAccountHolder())
                    .accountType(command.getAccountType())
                    .openingBalance(command.getOpeningBalance())
                    .build());
    }

    /**
     * @param amount to add
     * validate the account is active and amount greater than zero
     * create a correspondent event to execute changes
     */
    public void depositFounds(Double amount) {

        if (!this.active) throw new IllegalStateException("The found cant be deposit in disabled account");
        if (amount <= 0) throw new IllegalArgumentException("Cant deposit negative values");

        var event = FoundDepositedEvent
                .builder()
                .id(this.getId())
                .amount(amount)
                .build();

        super.raiseEvent(event);
    }


    /**
     * @param amount to withdraw
     * validate the account is active and amount greater than zero
     * create a correspondent event to execute changes
     */
    public void withdrawFounds(Double amount) {

        if (!this.active) throw new IllegalStateException("The found cant be deposit in disabled account");
        if (amount <= 0) throw new IllegalArgumentException("Cant deposit negative values");

        var event = FoundsWithdrawnEvent
                .builder()
                .id(this.getId())
                .amount(amount)
                .build();

        super.raiseEvent(event);
    }

    /**
     * validate the account is active
     * create a correspondent event to execute changes
     */
    public void closeAccount() {

        if (!this.active) throw new IllegalStateException("The found cant be deposit in disabled account");

        var event = AccountClosedEvent
                .builder()
                .id(this.getId())
                .build();

        super.raiseEvent(event);
    }

    private void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void apply(FoundsWithdrawnEvent event) {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void apply(FoundDepositedEvent event) {
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
        this.balance -= 0;
    }
}
