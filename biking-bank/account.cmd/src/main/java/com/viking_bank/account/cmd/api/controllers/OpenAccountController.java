package com.viking_bank.account.cmd.api.controllers;

import com.viking_bank.account.cmd.api.command.OpenAccountCommand;
import com.viking_bank.account.cmd.model.OpenAccountResponse;
import com.viking_bank.account.common.dto.BaseResponse;
import com.viking_bank.account.common.utils.CommonUtil;
import com.viking_bank.cqrs.core.infrastructure.CommandDispatcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/biking/api/open-account")
public class OpenAccountController {
    private final CommandDispatcher commandDispatcher;

    public OpenAccountController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> postOpenAccount(@RequestBody OpenAccountCommand command) {
        var id = CommonUtil.generateId();
        command.setId(id);
        this.commandDispatcher.send(command);
        return ResponseEntity.ok(OpenAccountResponse.
                builder()
                    .id(id)
                    .message("Account created")
                .build());
    }
}
