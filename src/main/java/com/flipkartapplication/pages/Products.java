/*
 *Purpose : Class is implemented to find the web elements in products page and  written the steps
 *                @FindBy annotation is used to find the web elements
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 16-07-2021
 */

package com.flipkartapplication.pages;

import com.flipkartapplication.base.BaseClass;
import com.flipkartapplication.utility.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Products extends BaseClass {

    @FindBy(xpath = "(//div[contains(concat(' ', normalize-space(@class), ' '), '_4rR01T')])[1]")
    WebElement product;
    @FindBy(xpath = "//li/button")
    WebElement addToCart;
    @FindBy(xpath = "//li/form/button" )
    WebElement buyNow;

    public Products(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * addProductToCart method is used to add the products to cart
     * @return current url of the page
     */
    public String addProductToCart() {
        Log.debug("click on product");
        product.click();
        String parentWindow = driver.getWindowHandle();
        Log.info("parent window details"+parentWindow);
        Set<String> windows = driver.getWindowHandles();
        Log.info("windows in browser" +windows);

        String currentUrl = null;
        for (String window:windows) {
            if (!parentWindow.equals(window)) {
                Log.debug("switch to window id of "+window);
                driver.switchTo().window(window);
                Log.debug("click on add to cart");
                addToCart.click();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                currentUrl = driver.getCurrentUrl();
            }
        }
        Log.info("switch to parent window"+parentWindow);
        driver.switchTo().window(parentWindow);

        Log.info("returns current url");
        return currentUrl;
    }
}
