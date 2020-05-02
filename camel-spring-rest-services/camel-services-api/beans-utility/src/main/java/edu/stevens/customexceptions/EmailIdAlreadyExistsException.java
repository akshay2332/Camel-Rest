package edu.stevens.customexceptions;

public class EmailIdAlreadyExistsException extends RuntimeException {

    public EmailIdAlreadyExistsException(String message) {
        super(message);
    }
}
