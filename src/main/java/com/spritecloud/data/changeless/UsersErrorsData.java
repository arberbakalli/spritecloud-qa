package com.spritecloud.data.changeless;

public enum UsersErrorsData {

    ERRORS_USER_NOT_FOUND("error", "user not found");
    //add more errors as needed

    public final String key;
    public final String message;

    UsersErrorsData(String key, String message) {
        this.key = key;
        this.message = message;
    }

}
