package edu.stevens.customexceptions;

import edu.stevens.response.Response;

public class UserIdAlreadyExistsException extends ValidationException {

    public UserIdAlreadyExistsException(Response message) {
        super(message);
    }
}
