package com.viking_bank.cqrs.core.events;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "event_model")
public class EventModel {

    @Id
    private String id;
    private LocalDateTime timestamp;
    private String aggregateIdentified;
    private String aggregateType;
    private Integer version;
    private String eventType;
    private BaseEvent eventData;
}
