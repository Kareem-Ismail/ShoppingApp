package com.example.ecommerce.api.enums;

public enum View {

    PRODUCTS("products"),
    PRODUCTS_REDIRECT("redirect:/getAllProducts"),
    ADD_PRODUCT("add_product");

    View(String viewName) {
        this.viewName = viewName;
    }

    private final String viewName;

    public String getViewName() {
        return viewName;
    }
}
