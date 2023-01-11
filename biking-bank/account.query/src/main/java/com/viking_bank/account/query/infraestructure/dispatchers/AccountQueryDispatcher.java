package com.viking_bank.account.query.infraestructure.dispatchers;

import com.viking_bank.cqrs.core.domain.entities.BaseEntity;
import com.viking_bank.cqrs.core.API.models.requests.BaseQueryRequest;
import com.viking_bank.cqrs.core.infrastructure.dispatchers.QueryDispatcher;
import com.viking_bank.cqrs.core.infrastructure.handlers.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {
    private final Map<Class<? extends BaseQueryRequest>, List<QueryHandlerMethod<? extends BaseQueryRequest>>>
            routes = new HashMap<>();

    @Override
    public <T extends BaseQueryRequest> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public List<BaseEntity> send(BaseQueryRequest query) {
        var handlersOptional = Optional.of(routes.get(query.getClass()));

        var handler = handlersOptional.orElseThrow(() -> new RuntimeException("no query was registered"));

         if (handler.size() > 1) throw new RuntimeException("Query must have one handler");

        return handler.get(0).handle(query);
    }

}
