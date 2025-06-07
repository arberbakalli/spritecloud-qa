package com.spritecloud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;

//    private By checkoutButton = By.id("checkout");
    private By checkoutButton = By.cssSelector("[data-test='checkout']");


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage proceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Dynamic wait
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }
}