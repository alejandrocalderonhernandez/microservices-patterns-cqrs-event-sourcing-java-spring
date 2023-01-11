package com.viking_bank.account.query.API.models;

import com.viking_bank.account.common.dto.BaseResponse;
import com.viking_bank.account.query.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountLookupResponse extends BaseResponse {
    private List<BankAccount> accounts;
}
