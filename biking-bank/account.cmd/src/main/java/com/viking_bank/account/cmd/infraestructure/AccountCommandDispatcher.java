package com.viking_bank.account.cmd.infraestructure;

import com.viking_bank.cqrs.core.comands.BaseCommand;
import com.viking_bank.cqrs.core.comands.CommandHandlerMethod;
import com.viking_bank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    /**
    * Contains a register the all commands given a key
     */
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod<? extends BaseCommand>>> routes;

    public AccountCommandDispatcher() {
        this.routes = new HashMap<>();
    }

    /**
     * @param type represents a command to handle class type
     * @param handler is the implementation concrete of the command  a through anonymous
     *  Add  in routes a new command
     */
    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    /**
     * @param command implementation of command to execute
     */
    @Override
    public void send(BaseCommand command) {
        var handlers = routes.get(command.getClass());

        if (Objects.isNull(handlers) || handlers.isEmpty()) throw new RuntimeException("The command are not registered");
        if (handlers.size() > 1) throw new RuntimeException("The command just must have one handler");

        handlers.get(0).handle(command);
    }
}
