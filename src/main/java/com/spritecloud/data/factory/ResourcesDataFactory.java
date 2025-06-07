package com.spritecloud.data.factory;

import com.spritecloud.data.changeless.EndPoints;
import com.spritecloud.model.Pages;
import com.spritecloud.model.ResourceBuilder;
import com.spritecloud.model.Resources;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Header;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ResourcesDataFactory {

    private static final Logger log = LogManager.getLogger(ResourcesDataFactory.class);

    private static final Faker faker = new Faker();

    private ResourcesDataFactory() {
    }

    public static Pages<Resources> allExistingResources() {
        var resource =
                given().header(new Header("x-api-key", "reqres-free-v1")). //this needs to be polished
                        when().
                        get(EndPoints.GET_UNKNOWN.getPath()).
                        then().
                        statusCode(HttpStatus.SC_OK).
                        extract().
                        as(new TypeRef<Pages<Resources>>() {
                        });

        log.info(resource);
        return resource;
    }

// Demonstrates creating new resources using the builder pattern, should be deleted otherwise
    public static Resources validResources() {
        return newResources();
    }

    private static Resources newResources() {
        var newResources =
                new ResourceBuilder().
                        id(faker.number().numberBetween(1, 1000)).
                        name(faker.name().nameWithMiddle()).
                        year(faker.number().numberBetween(2000, 2023)).
                        color(faker.color().name()).
                        pantone_value(faker.bothify("##-####")).build();

        log.info(newResources);
        return newResources;
    }
}