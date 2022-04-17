package com.darglk.ticketingcommons.events.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCreatedEventModel {
    private String id;
    private String orderId;
    private String stripeId;
}
