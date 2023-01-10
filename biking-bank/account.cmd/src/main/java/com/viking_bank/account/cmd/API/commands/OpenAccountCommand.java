package com.viking_bank.account.cmd.API.commands;

import com.viking_bank.account.common.dto.AccountType;
import com.viking_bank.cqrs.core.api.comands.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OpenAccountCommand extends BaseCommand {

    private String accountHolder;
    private AccountType accountType;
    private Double openingBalance;

}
