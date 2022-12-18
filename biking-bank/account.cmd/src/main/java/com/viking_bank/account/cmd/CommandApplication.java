package com.viking_bank.account.cmd;

import com.viking_bank.account.cmd.api.command.*;
import com.viking_bank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;
	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void init() {
		commandDispatcher.registerHandler(OpenAccountCommand.class,
				command -> commandHandler.handle((OpenAccountCommand) command));
		commandDispatcher.registerHandler(DepositFoundsCommand.class,
				command -> commandHandler.handle((DepositFoundsCommand) command));
		commandDispatcher.registerHandler(WithdrawFoundsCommand.class,
				command -> commandHandler.handle((WithdrawFoundsCommand) command));
		commandDispatcher.registerHandler(CloseAccountCommand.class,
				command -> commandHandler.handle((CloseAccountCommand) command));
	}

}
