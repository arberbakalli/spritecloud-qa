package com.spritecloud.data.factory;

import net.datafaker.Faker;

public class SauceDemoDataFactory {

  private static final Faker faker = new Faker();

  public static String getInvalidUsername() {
    return faker.internet().emailAddress();
  }

  public static String getInvalidPassword() {
    return faker.internet().password(8, 16, true, true, true);
  }
}
