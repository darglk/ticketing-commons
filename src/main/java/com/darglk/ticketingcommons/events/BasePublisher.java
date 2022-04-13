package com.darglk.ticketingcommons.events;

import com.darglk.ticketingcommons.events.types.Subjects;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.JetStream;
import io.nats.client.api.PublishAck;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Getter
public abstract class BasePublisher {
    private Subjects subject;
    private JetStream client;

    public CompletableFuture<PublishAck> publish(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        String dataStr = "";
        try {
            dataStr = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return this.client.publishAsync(subject.getSubject(), dataStr.getBytes());
    }
}
