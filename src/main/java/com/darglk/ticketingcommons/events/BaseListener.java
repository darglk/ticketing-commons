package com.darglk.ticketingcommons.events;

import com.darglk.ticketingcommons.events.types.Subjects;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.*;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public abstract class BaseListener {
    private Subjects subject;
    private String queueGroupName;
    private JetStream client;
    private Connection connection;

    abstract void onMessage(String data, Message msg);

    public void listen() {
        try {
            this.client.subscribe(
                    subject.getSubject(),
                    this.queueGroupName,
                    connection.createDispatcher(),
                    message -> {
                        String msg = this.parseMessage(message);
                        this.onMessage(msg, message);
                        },false,  PushSubscribeOptions.builder().build());
        } catch (IOException | JetStreamApiException e) {
            e.printStackTrace();
        }
    }

    private String parseMessage(Message msg) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(msg.getData(), String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Failed to parse message");
    }
}
