package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utils;

public class ShoppingCartSummary {


    private WebDriver driver;

    public ShoppingCartSummary(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCartProceedBtn() {
        return Utils.waitToBeClickable(driver, By.xpath("//div[@id='center_column']//a[@title='Proceed to checkout']/span"),30);
    }
}
