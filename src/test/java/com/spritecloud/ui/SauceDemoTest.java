package com.spritecloud.ui;

import static com.spritecloud.data.changeless.TestSuiteTags.UI;
import static org.assertj.core.api.Assertions.assertThat;

import com.spritecloud.data.changeless.UI_Checks;
import com.spritecloud.data.factory.SauceDemoDataFactory;
import com.spritecloud.pages.CartPage;
import com.spritecloud.pages.CheckoutPage;
import com.spritecloud.pages.LoginPage;
import com.spritecloud.pages.ProductsPage;
import java.util.Comparator;
import org.junit.jupiter.api.*;

public class SauceDemoTest extends TestSetup {

  @Test
  @DisplayName("Should complete a full checkout with two items and validate the final price")
  @Tag(UI)
  void completeCheckoutWithTwoItems() {
    LoginPage loginPage = new LoginPage(driver);
    ProductsPage productsPage = loginPage.login();
    CartPage cartPage =
        productsPage
            .addItemToCart("Sauce Labs Backpack")
            .addItemToCart("Sauce Labs Bike Light")
            .goToCart();
    CheckoutPage checkoutPage = cartPage.proceedToCheckout().enterDetails("John", "Doe", "12345");
    assertThat(checkoutPage.getFinalPrice()).isEqualTo(UI_Checks.CHECKOUT_FINAL_PRICE.getPath());
    checkoutPage.finishCheckout();
    assertThat(checkoutPage.getConfirmationMessage())
        .isEqualTo(UI_Checks.CHECKOUT_CONFIRMATION_MESSAGE.getPath());
  }

  @Test
  @DisplayName("Should sort items by name Z-A and validate sorting")
  @Tag(UI)
  void sortItemsByNameDescending() {
    LoginPage loginPage = new LoginPage(driver);
    ProductsPage productsPage = loginPage.login();
    productsPage.sortItemsBy("Name (Z to A)");
    assertThat(productsPage.getItemNames()).isSortedAccordingTo(Comparator.reverseOrder());
  }

  @Test
  @DisplayName("Should fail login with invalid credentials")
  @Tag(UI)
  void failLoginWithInvalidCredentials() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(
        SauceDemoDataFactory.getInvalidUsername(), SauceDemoDataFactory.getInvalidPassword());
    assertThat(loginPage.getErrorMessage()).isEqualTo(UI_Checks.LOGIN_PAGE_ERROR_MESSAGE.getPath());
  }
}
