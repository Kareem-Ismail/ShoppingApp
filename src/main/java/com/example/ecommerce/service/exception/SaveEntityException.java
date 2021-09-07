package com.example.ecommerce.service.exception;

import javax.persistence.PersistenceException;

public class SaveEntityException extends PersistenceException {
    private final String message;

    public SaveEntityException(String message) {
        this.message = message;
    }
}
