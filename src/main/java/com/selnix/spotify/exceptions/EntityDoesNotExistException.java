package com.selnix.spotify.exceptions;

public class EntityDoesNotExistException extends RuntimeException {
    public EntityDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
