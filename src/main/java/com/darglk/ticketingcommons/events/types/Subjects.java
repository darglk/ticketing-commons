package com.darglk.ticketingcommons.events.types;

import lombok.Getter;

@Getter
public enum Subjects {
    TicketCreated("ticket:created"),
    TicketUpdated("ticket:updated"),
    OrderCreated("order:created"),
    OrderCancelled("order:cancelled");

    Subjects(String subject) {
        this.subject = subject;
    }

    private String subject;
}
