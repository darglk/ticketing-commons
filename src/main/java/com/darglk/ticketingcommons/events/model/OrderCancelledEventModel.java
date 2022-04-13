package com.darglk.ticketingcommons.events.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelledEventModel {
    private String orderId;
    private Long orderVersion;
    private String ticketId;
}
