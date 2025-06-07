package com.spritecloud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage {
    private WebDriver driver;

    private By productNames = By.cssSelector(".inventory_item_name");
    private By addToCartButtons = By.cssSelector(".btn_inventory");
    private By sortDropdown = By.cssSelector(".product_sort_container");
    private By cartButton = By.id("shopping_cart_container");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductsPage addItemToCart(String itemName) {
        List<String> items = driver.findElements(productNames).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());

        int index = items.indexOf(itemName);
        if (index != -1) {
            driver.findElements(addToCartButtons).get(index).click();
        }
        return this;
    }

    public void sortItemsBy(String sortOption) {
        driver.findElement(sortDropdown).sendKeys(sortOption);
    }

    public List<String> getItemNames() {
        return driver.findElements(productNames).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    public CartPage goToCart() {
        driver.findElement(cartButton).click();
        return new CartPage(driver);
    }
}