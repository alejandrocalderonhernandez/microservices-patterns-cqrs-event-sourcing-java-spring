package com.viking_bank.cqrs.core.infrastructure;

import com.viking_bank.cqrs.core.domain.BaseEntity;
import com.viking_bank.cqrs.core.domain.BaseQueryRequest;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQueryRequest> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    List<BaseEntity> registerHandler(BaseQueryRequest query);
}
