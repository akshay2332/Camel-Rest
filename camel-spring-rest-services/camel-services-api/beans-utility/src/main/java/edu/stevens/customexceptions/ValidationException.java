package edu.stevens.customexceptions;

import edu.stevens.response.Response;

public class ValidationException extends RuntimeException implements CustomException {

    private Response response;

    public ValidationException(Response message) {
        super(message.getMessage());
        this.response = message;
    }


    @Override
    public Response getResponse() {
        return this.response;
    }
}
