package com.spritecloud.data.factory;

import com.spritecloud.data.changeless.EndPoints;
import com.spritecloud.model.*;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Header;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class UsersDataFactory {

    private static final Logger log = LogManager.getLogger(UsersDataFactory.class);
    private static final Faker faker = new Faker();

    private UsersDataFactory() {}

    public static Pages<Users> allExistingUsers() {
        var users =
                given().header(new Header("x-api-key", "reqres-free-v1")).
                        when().
                        get(EndPoints.GET_USERS.getPath()).
                        then().
                        statusCode(HttpStatus.SC_OK).
                        extract().
                        as(new TypeRef<Pages<Users>>() {});

        log.info(users);
        return users;
    }

    public static String randomUserJob() {
        String randomJob = faker.job().title();

        log.info("Random job generated: {}", randomJob);
        return randomJob;
    }

    public static Account createInvalidUser() {
        return new Account(faker.internet().emailAddress(), faker.internet().password());
    }
}