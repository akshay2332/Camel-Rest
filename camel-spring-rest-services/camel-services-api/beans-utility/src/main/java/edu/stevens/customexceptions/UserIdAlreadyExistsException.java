package edu.stevens.customexceptions;

public class UserIdAlreadyExistsException extends RuntimeException {

    public UserIdAlreadyExistsException(String message) {
        super(message);
    }
}
