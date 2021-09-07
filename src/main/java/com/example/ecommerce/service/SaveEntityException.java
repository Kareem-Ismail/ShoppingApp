package com.example.ecommerce.service;

import javax.persistence.PersistenceException;

public class SaveEntityException extends PersistenceException {
    private final String message;
    SaveEntityException(String message){
        this.message = message;
    }
}
