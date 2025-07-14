package com.spritecloud.users;

import static com.spritecloud.data.changeless.TestSuiteTags.CONTRACT;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

import com.spritecloud.BaseAPI;
import com.spritecloud.data.changeless.EndPoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UsersContractTest extends BaseAPI {

  @Test
  @Tag(CONTRACT)
  @DisplayName("Should validate the users schema for GET method")
  void getOneUser() {
    baseRequest
        .when()
        .get(EndPoints.GET_USERS.getPath())
        .then()
        .body(matchesJsonSchemaInClasspath("schemas/users_list_schema.json"))
        .statusCode(SC_OK);
  }

  @Test
  @Tag(CONTRACT)
  @DisplayName("Should validate the users schema for non-existing users")
  void simulationNotFound() {
    baseRequest
        .when()
        .get(EndPoints.GET_SINGLE_USER.getPath().formatted("id"), Integer.MAX_VALUE)
        .then()
        .body(matchesJsonSchemaInClasspath("schemas/user_not_exist_schema.json"))
        .statusCode(SC_NOT_FOUND);
  }
}
