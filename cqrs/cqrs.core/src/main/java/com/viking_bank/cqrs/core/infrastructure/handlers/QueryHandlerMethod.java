package com.viking_bank.cqrs.core.infrastructure.handlers;

import com.viking_bank.cqrs.core.domain.entities.BaseEntity;
import com.viking_bank.cqrs.core.api.models.requests.BaseQueryRequest;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQueryRequest> {
    List<BaseEntity> handle(BaseQueryRequest query);
}
