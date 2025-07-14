package com.spritecloud.resource;

import static com.spritecloud.data.changeless.TestSuiteTags.FUNCTIONAL;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;

import com.spritecloud.BaseAPI;
import com.spritecloud.data.changeless.EndPoints;
import com.spritecloud.data.factory.ResourcesDataFactory;
import com.spritecloud.data.provider.ResourcesDataProvider;
import com.spritecloud.model.Pages;
import com.spritecloud.model.Resources;
import io.restassured.common.mapper.TypeRef;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class ResourcesFunctionalTest extends BaseAPI {

  @Test
  @Tag(FUNCTIONAL)
  @DisplayName("Should validate all existing resources")
  void getAllExistingResources() {
    var resourcesPage =
        baseRequest
            .when()
            .get(EndPoints.GET_UNKNOWN.getPath())
            .then()
            .statusCode(SC_OK)
            .extract()
            .as(new TypeRef<Pages<Resources>>() {});

    // Final is best <3

    // here would be enough to stop but...
    Assertions.assertThat(resourcesPage.getData()).isNotEmpty();
    Assertions.assertThat(resourcesPage.getData().getFirst()).isInstanceOf(Resources.class);

    // why not do extra checks
    var existingResources = ResourcesDataFactory.allExistingResources();
    Assertions.assertThat(resourcesPage.getPage()).isEqualTo(existingResources.getPage());
    Assertions.assertThat(resourcesPage.getPer_page()).isEqualTo(existingResources.getPer_page());
    Assertions.assertThat(resourcesPage.getTotal()).isEqualTo(existingResources.getTotal());
    Assertions.assertThat(resourcesPage.getTotal_pages())
        .isEqualTo(existingResources.getTotal_pages());
    Assertions.assertThat(resourcesPage.getData()).hasSameSizeAs(existingResources.getData());

    resourcesPage
        .getData()
        .forEach(
            actualResource -> {
              var expectedResource =
                  existingResources.getData().stream()
                      .filter(resource -> resource.getId() == actualResource.getId())
                      .findFirst()
                      .orElseThrow(() -> new AssertionError("Expected resource not found"));

              Assertions.assertThat(actualResource.getId()).isEqualTo(expectedResource.getId());
              Assertions.assertThat(actualResource.getName()).isEqualTo(expectedResource.getName());
              Assertions.assertThat(actualResource.getYear()).isEqualTo(expectedResource.getYear());
              Assertions.assertThat(actualResource.getColor())
                  .isEqualTo(expectedResource.getColor());
              Assertions.assertThat(actualResource.getPantone_value())
                  .isEqualTo(expectedResource.getPantone_value());
            });
  }

  @Tag(FUNCTIONAL)
  @ParameterizedTest(name = "Scenario: {2}")
  @ArgumentsSource(ResourcesDataProvider.class)
  @DisplayName("Should validate all the invalid scenarios")
  void invalidSimulations(Integer invalidRecordPages, String path, String validationMessage) {

    baseRequest
        .param("page", invalidRecordPages)
        .when()
        .get(EndPoints.GET_UNKNOWN.getPath())
        .then()
        .statusCode(SC_OK)
        .body(path, is(Integer.parseInt(validationMessage)));
    // Status code should be negative with messages but due to the API implementation, it returns
    // 200
  }
}
