package com.spritecloud.users;

import static com.spritecloud.data.changeless.TestSuiteTags.FUNCTIONAL;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import com.spritecloud.BaseAPI;
import com.spritecloud.data.changeless.EndPoints;
import com.spritecloud.data.factory.UsersDataFactory;
import com.spritecloud.model.Pages;
import com.spritecloud.model.Users;
import io.restassured.common.mapper.TypeRef;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UsersFunctionalTest extends BaseAPI {

  @Test
  @Tag(FUNCTIONAL)
  @DisplayName("Should validate all existing users")
  void getAllExistingUsers() {
    var usersPage =
        baseRequest
            .when()
            .get(EndPoints.GET_USERS.getPath())
            .then()
            .statusCode(SC_OK)
            .extract()
            .as(new TypeRef<Pages<Users>>() {});

    Assertions.assertThat(usersPage.getData()).isNotEmpty();
    Assertions.assertThat(usersPage.getData().getFirst()).isInstanceOf(Users.class);

    var existingUsers = UsersDataFactory.allExistingUsers();
    Assertions.assertThat(usersPage.getPage()).isEqualTo(existingUsers.getPage());
    Assertions.assertThat(usersPage.getPer_page()).isEqualTo(existingUsers.getPer_page());
    Assertions.assertThat(usersPage.getTotal()).isEqualTo(existingUsers.getTotal());
    Assertions.assertThat(usersPage.getTotal_pages()).isEqualTo(existingUsers.getTotal_pages());
    Assertions.assertThat(usersPage.getData()).hasSameSizeAs(existingUsers.getData());

    usersPage
        .getData()
        .forEach(
            actualUser -> {
              var expectedUser =
                  existingUsers.getData().stream()
                      .filter(user -> user.getId() == actualUser.getId())
                      .findFirst()
                      .orElseThrow(() -> new AssertionError("Expected user not found"));

              Assertions.assertThat(actualUser.getId()).isEqualTo(expectedUser.getId());
              Assertions.assertThat(actualUser.getFirst_name())
                  .isEqualTo(expectedUser.getFirst_name());
              Assertions.assertThat(actualUser.getLast_name())
                  .isEqualTo(expectedUser.getLast_name());
              Assertions.assertThat(actualUser.getEmail()).isEqualTo(expectedUser.getEmail());
              Assertions.assertThat(actualUser.getAvatar()).isEqualTo(expectedUser.getAvatar());
            });
  }

  @Test
  @Tag(FUNCTIONAL)
  @DisplayName("Should updating an existing user")
  void changeUserSuccessfully() {
    var expectedJob = UsersDataFactory.randomUserJob();
    baseRequest
        .param("job", expectedJob)
        .when()
        .put(EndPoints.PUT_UPDATE_USER.getPath().formatted("id"), 2)
        .then()
        .statusCode(HttpStatus.SC_OK);
  }

  @Test
  @Tag(FUNCTIONAL)
  @DisplayName("Should delete an existing user")
  void deleteUserSuccessfully() {
    baseRequest
        .when()
        .delete(EndPoints.PUT_UPDATE_USER.getPath().formatted("id"), 3)
        .then()
        .statusCode(HttpStatus.SC_NO_CONTENT);
  }

  @Test
  @Tag(FUNCTIONAL)
  @DisplayName("Should fail to retrieve a non-existent user")
  void retrieveNonExistentUser() {
    baseRequest
        .when()
        .get(EndPoints.GET_SINGLE_USER.getPath().formatted("id"), Integer.MAX_VALUE)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @Tag(FUNCTIONAL)
  @DisplayName("Execute a delayed request and evaluate its duration (max 3 seconds)")
  void evaluateDelayedRequest() {
    baseRequest
        .when()
        .get(EndPoints.GET_DELAYED_RESPONSE.getPath().formatted("delay"), 3)
        .then()
        .statusCode(SC_OK)
        .assertThat()
        .time(lessThanOrEqualTo(3900L));
  }
}