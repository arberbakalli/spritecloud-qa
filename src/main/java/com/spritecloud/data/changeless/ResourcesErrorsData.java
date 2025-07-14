package com.spritecloud.data.changeless;

public enum ResourcesErrorsData {
  ERRORS_PAGES_GREATER("page", "2147483647");
  /* Due to the API the page and numbers are for a successful api, otherwise they would be error messages
  add all the possible combinations of errors that can be returned by the API:
  boundary testing, null empty values, invalid values, invalid data types, invalid formats */

  public final String key;
  public final String message;

  ResourcesErrorsData(String key, String message) {
    this.key = key;
    this.message = message;
  }
}
