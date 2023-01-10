package com.viking_bank.cqrs.core.api.models.requests;

import com.viking_bank.cqrs.core.domain.entities.EqualityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAccountWithBalanceQueryRequest extends BaseQueryRequest{
    private Double balance;
    private EqualityType type;
}
