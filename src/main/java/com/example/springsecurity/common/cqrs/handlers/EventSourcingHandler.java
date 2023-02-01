package com.example.springsecurity.common.cqrs.handlers;


import com.example.springsecurity.common.cqrs.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);
    T getById(String id);
    void republishEvents();
}
