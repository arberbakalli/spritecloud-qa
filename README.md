# Rest-Assured Assignment SauceDemo & reqres
[![Actions Status](https://github.com/eliasnogueira/restassured-complete-basic-example/workflows/Build%20and%20Test/badge.svg)](https://github.com/eliasnogueira/restassured-complete-basic-example/actions)

## About Me
Hi! Thank you for giving me the chance and opportunity to showcase what I know and how I approach problem solving.
I'm a passionate software engineer with a focus on building, scalable, and deploying test automation frameworks. This project is a demonstration of how to automate REST API testing using Rest-Assured, Maven, and Allure for reporting. I hope this serves as a helpful resource for anyone looking to get started with API test automation.

---

## Table of Contents
* [Required Software](#required-software)
* [How to Execute the Tests](#how-to-execute-the-tests)
  * [Running the Backend API](#running-the-backend-api)
  * [Running the Test Suites](#running-the-test-suites)
  * [Generating the Test Report](#generating-the-test-report)
* [About the Project Structure](#about-the-project-structure)
* [Libraries](#libraries)
* [Patterns Applied](#patterns-applied)
* [Pipeline](#pipeline)
* [Do You Want to Help?](#do-you-want-to-help)

---

## Required Software
* Java JDK 22
* Maven installed and in your classpath

> **Note**: You can use Java 17 if you want.

---

## How to Execute the Tests


### Running the Test Suites
You can run the test suites directly from your IDE or via the command line. Running via the command line is recommended for flexibility and pipeline integration.

To run all tests in Windows:
```bash
mvn test
```

To run all tests in macOS:
The test suites can be run directly by your IDE or by command line.
If you run `./mvnw test` all the tests will execute because it's the regular Maven lifecycle to run all the tests.

To run different suites based on the groups defined for each test you must inform the property `-Dgroups` and the group names.
The example below shows how to run the test for each pipeline stage:

| pipeline stage     | command                             |
|--------------------|-------------------------------------|
| ui tests           | `./mvnw test -Dgroups="ui"`         |
| contract tests     | `./mvnw test -Dgroups="contract"`   |
| functional tests   | `./mvnw test -Dgroups="functional"` |
| e2e tests          | `./mvnw test -Dgroups="e2e"`        |

### Generating the test report

This project uses Allure Report to automatically generate the test report.
There are some configuration to make it happen:
* aspectj configuration on `pom.xml` file
* `allure.properties` file on `src/test/resources`

MacOS You can use the command line to generate it in two ways:
* `./mvnw allure:serve`: will open the HTML report into the browser
* `./mvnw allure:report`: will generate the HTML port at `target/site/allure-maven-plugin` folder

Windows You can use the command line to generate it in two ways:
* `mvn allure:serve`: will open the HTML report into the browser
* `mvn allure:report`: will generate the HTML port at `target/site/allure-maven-plugin` folder

## About the Project Structure

### src/main/java

#### client
Classes that do some actions in their endpoints. It's used my the `FullSimulationE2ETest` to demonstrate and e2e
scenario.

#### commons
It contains a class where will format the URL expected when we create a new resource in the `shared enum` endpoint.
You can add any class that can be used in the project.

#### config
The class `Configuration` is the connections between the property file `api.properties` located in `src/test/resources/`.

The `@Config.Sources` load the properties file and match the attributes with the `@Key`, so you automatically have the value.
You can see two sources.
The first one will get the property values from the system (as environment variables or from the command line) in the case you want to change it, for example, in a pipeline.
The second will load the `api.properties` file from the classpath.
```java
@Config.Sources({
    "system:properties",
    "classpath:api.properties"})
```

The environment variable is read on the `ConfiguratorManager`.
This class reduces the amount of code necessary to get any information on the properties file.

This strategy uses [Owner](https://matteobaccan.github.io/owner/) library

#### data

##### factory
Test Data Factory classes using [java-faker](https://github.com/DiUS/java-faker) to generate fake data and [Lombok] to
create the objects using the Builder pattern.

##### provider
JUnit 5 Arguments to reduce the amount of code and maintenance for the functional tests on `SimulationsFunctionalTest`

##### suite
It contains a class having the data related to the test groups.

#### model
Model and Builder class to
[mapping objects thought serialization and deserialization](https://github.com/rest-assured/rest-assured/wiki/Usage#object-mapping)
in use with Rest-Assured.

#### specs
Request and Response specifications used by the clients and e2e tests.

### src/test/java

#### e2e
End-to-End test using both endpoints to simulate the user journey thought the API.

#### general
Login check test to assure the endpoint is available.

#### UI
UI endpoint is available.

#### Resources
Contract and Functional tests to the Restriction endpoint.

#### Users
Contract and Functional tests to the Users endpoint

### src/test/resources
It has a `schemas` folder with the JSON Schemas to enable Contract Testing using Rest-Assured. Also, the properties file to easily configure the API URI.

## Libraries
* [RestAssured](http://rest-assured.io/) library to test REST APIs
* [JUnit 5](https://junit.org/junit5/) to support the test creation
* [Owner](https://matteobaccan.github.io/owner/) to manage the property files
* [java-faker](https://github.com/DiUS/java-faker) to generate fake data
* [Log4J2](https://logging.apache.org/log4j/2.x/) as the logging strategy
* [Allure Report](https://docs.qameta.io/allure/) as the testing report strategy

## Patterns applied
* Test Data Factory
* Data Provider
* Builder
* Request and Response Specification
* Base Test

## Pipeline

This project uses [GitHub Actions](https://github.com/features/actions) to run the all the tests in a pipeline.

We have the following pipeline steps:
```
build -> ui -> contract -> e2d -> funcional 
```

Except the build, that is the traditional Maven build, the other stages has some parameters to determine the test type and the SUT (System Under Test).
The parameters are:
* `-Dgroups`: specify which test type will be executed
* `-Dapi.base.uri`: specify a new base URI
* `-Dapi.base.path`: specify a new base path

All the parameters, except the `-Dgroups` are pointing to Heroku because we can't run it locally.
It's a great example about how can you set different attribute values to run your tests.