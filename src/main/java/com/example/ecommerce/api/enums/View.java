package com.example.ecommerce.api.enums;

public enum View {

    PRODUCTS("products");

    View(String viewName) {
        this.viewName = viewName;
    }

    private final String viewName;

    public String getViewName() {
        return viewName;
    }
}
