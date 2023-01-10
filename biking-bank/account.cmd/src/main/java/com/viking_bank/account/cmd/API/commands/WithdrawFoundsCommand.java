package com.viking_bank.account.cmd.API.commands;

import com.viking_bank.cqrs.core.api.comands.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WithdrawFoundsCommand extends BaseCommand {

    private Double amount;
}
