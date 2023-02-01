package com.example.springsecurity.common.cqrs.infrastructure;


import com.example.springsecurity.common.cqrs.domain.BaseEntity;
import com.example.springsecurity.common.cqrs.queries.BaseQuery;
import com.example.springsecurity.common.cqrs.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
