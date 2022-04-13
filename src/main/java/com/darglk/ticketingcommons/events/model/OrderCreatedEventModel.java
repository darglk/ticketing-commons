package com.darglk.ticketingcommons.events.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEventModel {
    private String orderId;
    private String orderStatus;
    private String userId;
    private Long version;
    private String expiresAt;
    private String ticketId;
    private String ticketPrice;
}
