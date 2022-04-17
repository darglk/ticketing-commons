package com.darglk.ticketingcommons.events.model;

import com.darglk.ticketingcommons.events.types.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEventModel {
    private String orderId;
    private OrderStatus orderStatus;
    private String userId;
    private Long version;
    private String expiresAt;
    private String ticketId;
    private String ticketPrice;
}
