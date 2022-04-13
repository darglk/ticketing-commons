package com.darglk.ticketingcommons.events.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreatedEventModel {
    private String id;
    private Long version;
    private String title;
    private String price;
    private String userId;
}
