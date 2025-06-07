package com.spritecloud.client;

import com.spritecloud.data.changeless.UsersData;
import com.spritecloud.specs.UsersSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersClient {

    public Response queryUsersAndReturnUnAuthorized(){
        return given().
                spec(UsersSpecs.UsersWithoutRestrictionRequestSpec()).
                when().
                post(UsersData.GET_RESTRICTIONS).
                then().
                spec(UsersSpecs.notFoundResponse()).
                extract().
                response();
    }
}
