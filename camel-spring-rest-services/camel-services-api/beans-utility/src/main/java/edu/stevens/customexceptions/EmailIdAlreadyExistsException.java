package edu.stevens.customexceptions;

import edu.stevens.response.Response;

public class EmailIdAlreadyExistsException extends ValidationException {

    public EmailIdAlreadyExistsException(Response message) {
        super(message);
    }
}
