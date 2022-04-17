package com.darglk.ticketingcommons.events.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCreatedEventModel {
    private String id;
    private Long version;
    private String title;
    private String price;
    private String userId;
}
