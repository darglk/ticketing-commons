package com.darglk.ticketingcommons.exception;

import java.util.List;

public class NotFoundException extends CustomException {
    public NotFoundException(String message) {
        super(message, 404);
    }

    @Override
    public List<ErrorResponse> serializeErrors() {
        return List.of(
                new ErrorResponse(getMessage(), null)
        );
    }
}
