package com.spritecloud.resource;

import static com.spritecloud.data.changeless.TestSuiteTags.CONTRACT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_OK;

import com.spritecloud.BaseAPI;
import com.spritecloud.data.changeless.EndPoints;
import io.restassured.http.Header;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ResourcesContractTest extends BaseAPI {

  @Test
  @Tag(CONTRACT)
  @DisplayName("Should validate the resource schema for GET method")
  void getOneUser() {
    given()
        .header(new Header("x-api-key", "reqres-free-v1"))
        .when()
        .get(EndPoints.GET_UNKNOWN.getPath())
        .then()
        .body(matchesJsonSchemaInClasspath("schemas/resources_list_schema.json"))
        .statusCode(SC_OK);
  }
}
