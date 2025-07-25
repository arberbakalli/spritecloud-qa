package com.spritecloud.model;

import java.util.Objects;

public class Users implements Data {

  private int id;
  private String email;
  private String first_name;
  private String last_name;
  private String avatar;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  @Override
  public String toString() {
    return "Users(id="
        + this.getId()
        + ", email="
        + this.getEmail()
        + ", firstName="
        + this.getFirst_name()
        + ", lastName="
        + this.getLast_name()
        + ", avatar="
        + this.getAvatar()
        + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Users users = (Users) o;
    return id == users.id
        && Objects.equals(email, users.email)
        && Objects.equals(first_name, users.first_name)
        && Objects.equals(last_name, users.last_name)
        && Objects.equals(avatar, users.avatar);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, first_name, last_name, avatar);
  }
}
