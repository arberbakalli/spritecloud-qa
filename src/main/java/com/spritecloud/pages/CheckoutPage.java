package com.spritecloud.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
  private WebDriver driver;

  private By firstNameField = By.id("first-name");
  private By lastNameField = By.id("last-name");
  private By postalCodeField = By.id("postal-code");
  private By continueButton = By.id("continue");
  private By finishButton = By.id("finish");
  private By itemPrice = By.cssSelector("[data-test='subtotal-label']");

  //    private By itemPrice = By.className("summary_subtotal_label");
  private By taxPrice = By.cssSelector("[data-test='tax-label']");
  private By finalPrice = By.cssSelector("[data-test='total-label']");
  private By finalPricee = By.className("summary_total_label");

  public CheckoutPage(WebDriver driver) {
    this.driver = driver;
  }

  public CheckoutPage enterDetails(String firstName, String lastName, String postalCode) {
    driver.findElement(firstNameField).sendKeys(firstName);
    driver.findElement(lastNameField).sendKeys(lastName);
    driver.findElement(postalCodeField).sendKeys(postalCode);
    driver.findElement(continueButton).click();
    return this;
  }

  public CheckoutPage finishCheckout() {
    driver.findElement(finishButton).click();
    return this;
  }

  //    public String getFinalPrice() {
  //        return driver.findElement(finalPrice).getText();
  //    }

  public String getFinalPrice() {
    String regex = "[0-9\\.]+";
    double subtotal =
        Double.parseDouble(extractValue(driver.findElement(itemPrice).getText(), regex));
    double tax = Double.parseDouble(extractValue(driver.findElement(taxPrice).getText(), regex));
    double total =
        Double.parseDouble(extractValue(driver.findElement(finalPrice).getText(), regex));

    if (Math.abs((subtotal + tax) - total) > 0.01) {
      throw new IllegalStateException("Subtotal and tax do not add up to the total.");
    }

    return String.format("$%.2f", total);
  }

  private String extractValue(String text, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    if (matcher.find()) {
      return matcher.group();
    }
    throw new IllegalArgumentException("No numeric value found in the text: " + text);
  }

  public String getConfirmationMessage() {
    return driver.findElement(By.className("title")).getText();
  }
}
