package com.darglk.ticketingcommons.events;

import com.darglk.ticketingcommons.events.types.Subjects;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.*;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public abstract class BaseListener<T> {
    private Subjects subject;
    private String queueGroupName;
    private JetStream client;
    private Connection connection;

    public abstract void onMessage(T data, Message msg);

    public void listen(Class<T> clazz) {
        try {
            this.client.subscribe(
                    subject.getSubject(),
                    this.queueGroupName,
                    connection.createDispatcher(),
                    message -> {
                        T msg = this.parseMessage(message, clazz);
                        this.onMessage(msg, message);
                        },false,  PushSubscribeOptions.builder().build());
        } catch (IOException | JetStreamApiException e) {
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
