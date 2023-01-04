package com.viking_bank.cqrs.core.infrastructure;

import com.viking_bank.cqrs.core.domain.BaseEntity;
import com.viking_bank.cqrs.core.domain.BaseQueryRequest;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQueryRequest> {
    List<BaseEntity> handle(BaseQueryRequest query);
}
