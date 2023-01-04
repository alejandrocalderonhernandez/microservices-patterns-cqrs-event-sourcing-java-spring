package com.viking_bank.cqrs.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAccountByHolderQueryRequest extends BaseQueryRequest {
    private String accountHolder;
}
