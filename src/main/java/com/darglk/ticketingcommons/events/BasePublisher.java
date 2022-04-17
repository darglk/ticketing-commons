package com.darglk.ticketingcommons.events;

import com.darglk.ticketingcommons.events.types.Subjects;
import com.darglk.ticketingcommons.utils.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Getter
@Slf4j
public abstract class BasePublisher {
    private Subjects subject;
    private Connection connection;

    public void publish(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        String dataStr = JSONUtils.toJson(data);

        log.info("Publishing event {} to subject {}", dataStr, subject.getSubject());
        this.connection.publish(subject.getSubject(), dataStr.getBytes());
    }
}
