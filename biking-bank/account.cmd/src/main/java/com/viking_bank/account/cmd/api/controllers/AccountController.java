package com.viking_bank.account.cmd.api.controllers;

import com.viking_bank.account.cmd.api.command.CloseAccountCommand;
import com.viking_bank.account.cmd.api.command.DepositFoundsCommand;
import com.viking_bank.account.cmd.api.command.OpenAccountCommand;
import com.viking_bank.account.cmd.api.command.WithdrawFoundsCommand;
import com.viking_bank.account.cmd.domain.models.OpenAccountResponse;
import com.viking_bank.account.common.dto.BaseResponse;
import com.viking_bank.account.common.utils.CommonUtil;
import com.viking_bank.cqrs.core.infrastructure.CommandDispatcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "biking/api/account")
public class AccountController {
    private final CommandDispatcher commandDispatcher;

    public AccountController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {
        var id = CommonUtil.generateId();
        command.setId(id);
        this.commandDispatcher.send(command);
        var r = OpenAccountResponse.
                builder()
                .id(id)
                .message("Account created")
                .build();
        return ResponseEntity.ok(r);
    }

    @PatchMapping(path = "deposit/{id}")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable String id, @RequestBody DepositFoundsCommand command) {
        command.setId(id);
        this.commandDispatcher.send(command);
        return ResponseEntity.ok(OpenAccountResponse.
                builder()
                    .id(id)
                    .message("Deposit success")
                .build());
    }

    @PatchMapping(path = "withdraw/{id}")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable String id, @RequestBody WithdrawFoundsCommand command) {
        command.setId(id);
        this.commandDispatcher.send(command);
        return ResponseEntity.ok(OpenAccountResponse.
                builder()
                    .id(id)
                    .message("Deposit success")
                .build());
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity<Void> closeAccount(@PathVariable String id) {
        var command = new CloseAccountCommand(id);
        this.commandDispatcher.send(command);
        return ResponseEntity.noContent().build();
    }
}
