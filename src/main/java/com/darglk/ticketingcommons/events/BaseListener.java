package com.darglk.ticketingcommons.events;

import com.darglk.ticketingcommons.events.types.Subjects;
import com.darglk.ticketingcommons.utils.JSONUtils;
import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class BaseListener<T> {
    private Subjects subject;
    private String queueGroupName;
    private JetStream client;
    private Connection connection;

    public abstract void onMessage(T data, Message msg);

    public void listen(Class<T> clazz) {
        this.connection
                .createDispatcher(msg -> onMessage(JSONUtils.fromJson(new String(msg.getData()), clazz), msg))
                .subscribe(subject.getSubject(), queueGroupName);
    }
}
