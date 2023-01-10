package com.viking_bank.cqrs.core.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAccountByIdQueryRequest extends BaseQueryRequest{
    private String id;
}
