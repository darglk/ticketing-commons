package com.darglk.ticketingcommons.exception;

import java.util.List;

public class NotAuthorizedException extends CustomException {
    public NotAuthorizedException() {
        super("Unauthorized", 401);
    }

    @Override
    public List<ErrorResponse> serializeErrors() {
        return List.of(
                new ErrorResponse("Not authorized", null)
        );
    }
}
