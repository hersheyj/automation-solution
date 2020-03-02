package com.test;

import com.pageObjects.Products;
import com.pageObjects.Registration;
import com.pageObjects.ShoppingActions;
import com.pageObjects.ShoppingCartSummary;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.Utils;

import java.util.List;

public class SampleTest {

    private WebDriver driver;
    private Actions action;
    private Products products;
    private ShoppingActions shoppingActions;
    private ShoppingCartSummary shoppingCartSummary;
    private Registration registration;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\harsh.jain\\Downloads\\chromedriverfinal\\chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        products = new Products(driver);
        shoppingActions = new ShoppingActions(driver);
        shoppingCartSummary = new ShoppingCartSummary(driver);
        registration = new Registration(driver);
        String baseUrl = "http://automationpractice.com/index.php";
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterClass
    public void closeAll() {
//        driver.quit();
    }

    //approach using the home page to add the summer dresses
//    @Test
//    public void getProductNames() {
//        List<WebElement> listElement = driver.findElements(By.className("product-name"));
//        for (int i = 0; i < listElement.size(); i++) {
//            String elementText = listElement.get(i).getText();
//            System.out.println(elementText);
//
//            if (elementText.contains("Printed Summer Dress")){
//                System.out.println(products.getSummerDressProduct(1));
//
//            }
//        }
//    }

    // Filter Summer Dresses and Add to Cart
    @Test
    public void addProducts() {
        //Check annd Navigate to summer dresses
        Assert.assertTrue(products.getDressesBtn().isDisplayed());
        action.moveToElement(products.getDressesBtn()).perform();
        Assert.assertTrue(products.getSummerDressesBtn().isDisplayed());
        action.moveToElement(products.getSummerDressesBtn()).perform();
        products.getSummerDressesBtn().click();

        //Confirm summer dresses are displayed as expected
        Assert.assertTrue(products.getSummerDressProduct(1).isDisplayed());
        Assert.assertTrue(products.getSummerDressProduct(2).isDisplayed());
        Assert.assertTrue(products.getSummerDressProduct(3).isDisplayed());
        Assert.assertEquals(products.getDressesCount().size(), 3);

        //add  the first dress to the cart and proceed to checkout
        action.moveToElement(products.getSummerDressProduct(1)).perform();
        action.moveToElement(shoppingActions.getAddToCartBtn()).perform();
        Assert.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());
        action.click(shoppingActions.getAddToCartBtn()).build().perform();
        Assert.assertTrue(shoppingActions.getSuccessfullyAddedMessage().isDisplayed());
        Assert.assertTrue(shoppingActions.getProceedToCheckoutBtn().isDisplayed());
        action.click(shoppingActions.getProceedToCheckoutBtn()).build().perform();

        //Proceed to checkout
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 650);");
        Assert.assertTrue(shoppingCartSummary.getCartProceedBtn().isDisplayed());
        action.moveToElement(shoppingCartSummary.getCartProceedBtn()).perform();
        action.click(shoppingCartSummary.getCartProceedBtn()).build().perform();

        //Confirm Sign In is available
        registration.signInButton().isDisplayed();

    }
}