package com.example.springsecurity.common.cqrs.queries;


import com.example.springsecurity.common.cqrs.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
    List<BaseEntity> handle(T query);
}
