package com.spritecloud.data.changeless;

public enum UI_Checks {

    LOGIN_PAGE_ERROR_MESSAGE("Epic sadface: Username and password do not match any user in this service", "Login page error message"),
    CHECKOUT_FINAL_PRICE("$43.18","Final amount" ),
    CHECKOUT_CONFIRMATION_MESSAGE("Checkout: Complete!"," Successfully checked out" );

    private final String path;
    private final String description;

    UI_Checks(String path, String description) {
        this.path = path;
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }
}
