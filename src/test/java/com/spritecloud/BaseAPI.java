package com.spritecloud;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import com.spritecloud.config.Configuration;
import com.spritecloud.config.ConfigurationManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseAPI {

  protected static Configuration configuration;
  protected static RequestSpecification baseRequest;

  @BeforeAll
  public static void beforeAllTests() {
    configuration = ConfigurationManager.getConfiguration();

    baseURI = configuration.baseURI();
    basePath = configuration.basePath();

    baseRequest =
        given().header(configuration.key(), configuration.value()).contentType(ContentType.JSON);

    RestAssured.useRelaxedHTTPSValidation();

    determineLog();
  }

  private static void determineLog() {
    if (configuration.logAll()) {
      RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    } else {
      RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
  }
}