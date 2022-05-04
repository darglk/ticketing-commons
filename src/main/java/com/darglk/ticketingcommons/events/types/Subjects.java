package com.darglk.ticketingcommons.events.types;

import lombok.Getter;

@Getter
public enum Subjects {
    TicketCreated("ticket_created"),
    TicketUpdated("ticket_updated"),
    OrderCreated("order_created"),
    OrderCancelled("order_cancelled"),
    ExpirationComplete("expiration_complete"),
    PaymentCreated("payment_created");

    Subjects(String subject) {
        this.subject = subject;
    }

    private String subject;
}
