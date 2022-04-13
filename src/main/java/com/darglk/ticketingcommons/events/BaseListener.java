package com.darglk.ticketingcommons.events;

import com.darglk.ticketingcommons.events.types.Subjects;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

@AllArgsConstructor
@Getter
public abstract class BaseListener<T> {
    private Subjects subject;
    private String queueGroupName;
    private JetStream client;
    private Connection connection;

    public abstract void onMessage(T data, Message msg);

    public void listen(Class<T> clazz) {
        try {
            final var sub = this.connection.subscribe(subject.getSubject(), queueGroupName);
            final var mesage = sub.nextMessage(0);
            onMessage(parseMessage(mesage, clazz), mesage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private T parseMessage(Message msg, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(msg.getData(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Failed to parse message");
    }
}
