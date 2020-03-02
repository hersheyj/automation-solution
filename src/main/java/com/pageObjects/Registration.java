package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Registration {
    private WebDriver driver;

    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement signInButton(){
        return driver.findElement(By.id("SubmitLogin"));
    }


}
