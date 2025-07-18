package com.spritecloud.client;

import static io.restassured.RestAssured.given;

import com.spritecloud.data.changeless.ResourcesData;
import com.spritecloud.specs.ResourcesSpecs;
import io.restassured.response.Response;

public class ResourcesClient {

  public Response queryRecordsAndReturnUnAuthorized() {
    return given()
        .spec(ResourcesSpecs.shouldBePositiveScenarioButOutOfTime())
        .when()
        .post(ResourcesData.GET_RESTRICTIONS)
        .then()
        .spec(ResourcesSpecs.shouldCreatedResources())
        .extract()
        .response();
  }
}
