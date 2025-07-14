package com.spritecloud.data.changeless;

public enum EndPoints {
  GET_USERS("users", "Retrieve all users"),
  GET_UNKNOWN("unknown", "Retrieve all unknown resources"),
  PUT_UPDATE_USER("users/{id}", "Update an existing user by ID"),
  POST_LOGIN("login", "Log in a user"),
  GET_DELAYED_RESPONSE("users?delay={delay}", "Retrieve users with a delayed response"),
  GET_SINGLE_USER("users/{id}", "Retrieve a single user by ID"),
  POST_CREATE_USER("users", "Create a new user"),
  GET_SINGLE_UNKNOWN("unknown/{id}", "Retrieve a single unknown resource by ID"),
  PATCH_UPDATE_USER("users/{id}", "Partially update an existing user by ID"),
  DELETE_USER("users/{id}", "Delete a user by ID"),
  POST_REGISTER("register", "Register a new user");

  private final String path;

  private final String description;

  EndPoints(String path, String description) {
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
