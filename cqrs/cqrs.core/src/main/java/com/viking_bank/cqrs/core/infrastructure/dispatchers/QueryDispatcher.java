package com.viking_bank.cqrs.core.infrastructure.dispatchers;

import com.viking_bank.cqrs.core.domain.entities.BaseEntity;
import com.viking_bank.cqrs.core.API.models.requests.BaseQueryRequest;
import com.viking_bank.cqrs.core.infrastructure.handlers.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQueryRequest> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    List<BaseEntity> send(BaseQueryRequest query);
}
