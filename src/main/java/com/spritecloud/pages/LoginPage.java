package com.spritecloud.pages;

import com.spritecloud.config.Configuration;
import com.spritecloud.config.ConfigurationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private static Configuration configuration = ConfigurationManager.getConfiguration();

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector(".error-message-container");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductsPage login() {
        driver.findElement(usernameField).sendKeys(configuration.superAdmin());
        driver.findElement(passwordField).sendKeys(configuration.superPassword());
        driver.findElement(loginButton).click();
        return new ProductsPage(driver);
    }

    public ProductsPage login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return new ProductsPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}