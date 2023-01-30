package com.example.springsecurity.entity;

import io.cqrs.events.BaseEvent;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Document(collection = "eventStore")
public class EventModel {
    @Id private String id;
    private Date timeStamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private int version;
    private String eventType;
    private BaseEvent eventData;
}
