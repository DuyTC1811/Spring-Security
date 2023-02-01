package com.example.springsecurity.common.cqrs.producers;


import com.example.springsecurity.common.cqrs.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
