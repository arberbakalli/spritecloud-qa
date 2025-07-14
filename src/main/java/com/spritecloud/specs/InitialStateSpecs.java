package com.spritecloud.specs;

import com.spritecloud.config.ConfigurationManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class InitialStateSpecs {
  public static RequestSpecification set() {
    var configuration = ConfigurationManager.getConfiguration();

    return new RequestSpecBuilder()
        .setBaseUri(configuration.baseURI())
        .setBasePath(configuration.basePath())
        .build();
  }
}
