package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utils;

import java.util.List;

public class Products {


    private WebDriver driver;

    public Products(WebDriver driver) {
        this.driver = driver;
    }
    
    public WebElement getDressesBtn() {
        return Utils.waitToBeClickable(driver, By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]"), 30);
    }

    public WebElement getSummerDressesBtn() {
        return Utils.waitToBeClickable(driver, By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]//a[contains(text(), \"Summer Dresses\")]"), 30);
    }

    public List<WebElement> getDressesCount() {
        return driver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li"));
    }

    public WebElement getSummerDressProduct(int dressNum) {
            return Utils.waitForElementPresence(driver, By.xpath("//*[@id=\"center_column\"]/ul/li[" + dressNum + "]"), 30);
    }
}
