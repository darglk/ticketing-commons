package com.darglk.ticketingcommons.exception;

import java.util.List;

public class BadRequestException extends CustomException {
    public BadRequestException(String message) {
        super(message, 400);
    }

    @Override
    public List<ErrorResponse> serializeErrors() {
        return List.of(
                new ErrorResponse(this.getMessage(), null)
        );
    }
}
