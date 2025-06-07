package com.spritecloud.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class UsersSpecs {

    private UsersSpecs() {
    }

    public static RequestSpecification UsersWithoutRestrictionRequestSpec() {

        return new RequestSpecBuilder().
                addRequestSpecification(InitialStateSpecs.set()).
                build();
    }

    public static ResponseSpecification notFoundResponse() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE).build();
    }
}
