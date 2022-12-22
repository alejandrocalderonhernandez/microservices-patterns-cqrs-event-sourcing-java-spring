package com.viking_bank.account.cmd.model;

import com.viking_bank.account.common.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountResponse extends BaseResponse {

    private String id;
}
