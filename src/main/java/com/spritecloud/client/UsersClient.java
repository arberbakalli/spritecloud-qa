package com.spritecloud.client;

import static io.restassured.RestAssured.given;

import com.spritecloud.data.changeless.UsersData;
import com.spritecloud.specs.UsersSpecs;
import io.restassured.response.Response;

public class UsersClient {

  public Response queryUsersAndReturnUnAuthorized() {
    return given()
        .spec(UsersSpecs.UsersWithoutRestrictionRequestSpec())
        .when()
        .post(UsersData.GET_RESTRICTIONS)
        .then()
        .spec(UsersSpecs.notFoundResponse())
        .extract()
        .response();
  }
}
