package com.example.ecommerce.service.enums;

public enum ResponseMessage {

    SUCCESS("Action Succeeded"),
    SQL_SAVING_EXCEPTION("Saving %s Failed"),
    FAILED("Action failed");

    ResponseMessage(String message) {
        this.message = message;
    }
    private final String message;

    public String getMessage() {
        return message;
    }
}
