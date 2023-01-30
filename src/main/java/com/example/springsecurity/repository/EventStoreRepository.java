package com.example.springsecurity.repository;

import com.example.springsecurity.entity.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventStoreRepository extends MongoRepository<EventModel,String> {
    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}
