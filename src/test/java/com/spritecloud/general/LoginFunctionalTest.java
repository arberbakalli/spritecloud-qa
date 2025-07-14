package com.spritecloud.general;

import static com.spritecloud.data.changeless.TestSuiteTags.FUNCTIONAL;
import static org.hamcrest.CoreMatchers.equalTo;

import com.spritecloud.BaseAPI;
import com.spritecloud.data.changeless.EndPoints;
import com.spritecloud.data.changeless.UsersErrorsData;
import com.spritecloud.data.factory.UsersDataFactory;
import com.spritecloud.model.Account;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LoginFunctionalTest extends BaseAPI {

  @Test
  @Tag(FUNCTIONAL)
  @DisplayName("Should login successfully")
  void userLoginSuccessfully() {
    baseRequest
        .when()
        .body(new Account(configuration.userName(), configuration.password()))
        .post(EndPoints.POST_LOGIN.getPath())
        .then()
        .statusCode(HttpStatus.SC_OK)
        .body("token", equalTo(configuration.expectedToken()));
  }

  @Test
  @Tag(FUNCTIONAL)
  @DisplayName("Should fail login with invalid credentials")
  void loginWithInvalidCredentials() {
    baseRequest
        .body(UsersDataFactory.createInvalidUser())
        .when()
        .post(EndPoints.POST_LOGIN.getPath())
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST)
        .body(
            UsersErrorsData.ERRORS_USER_NOT_FOUND.key,
            equalTo(UsersErrorsData.ERRORS_USER_NOT_FOUND.message));
  }
}
