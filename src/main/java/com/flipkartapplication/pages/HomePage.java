/*
 *Purpose : Class is implemented to find the web elements in homepage and  written the steps
 *                @FindBy annotation is used to find the web elements
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 15-07-2021
 */
package com.flipkartapplication.pages;

import com.flipkartapplication.base.BaseClass;
import com.flipkartapplication.utility.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class HomePage extends BaseClass {

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchBox;
    @FindBy(className = "L0Z3Pu")
    WebElement searchButton;
    @FindBy(xpath = "//div[@class='_2Xfa2_']/div[3]/div/div/div/div")
    public WebElement profileDropDown;
    @FindBy(xpath = "//div[contains(text(),'My Profile')]")
    WebElement myProfile;
    @FindBy(xpath = "//div[contains(text(),'Orders')]")
    WebElement orders;
    @FindBy(xpath = "//div[contains(text(),'Wishlist')]")
    WebElement wishlist;
    @FindBy(xpath = "//div[contains(text(),'Logout')]")
    public WebElement logout;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * openMyProfile method is used to open my profile page
     *
     * @return current url of the page
     */
    public String openMyProfile() {
        mouseHoverActions(profileDropDown, myProfile);
        Log.debug("click on my profile");
        myProfile.click();

        Log.info(" returns current url of page");
        return driver.getCurrentUrl();
    }

    /**
     * goToOrders method is used to navigate to orders page
     *
     * @return current url of page
     */
    public String goToOrders() {
        mouseHoverActions(profileDropDown, orders);
        Log.debug("click on orders");
        orders.click();

        Log.info("returns current url of page");
        return driver.getCurrentUrl();
    }

    /**
     * goToWishlist method is used to navigate the wishlist page
     *
     * @return current url of page
     */
    public String goToWishlist() {
        mouseHoverActions(profileDropDown, wishlist);
        Log.debug("click on wishlist");
        wishlist.click();

        Log.info("returns current url of page");
        return driver.getCurrentUrl();
    }

    /**
     * logoutFromApplication method is used to logout from application
     *
     * @return current url of page
     */
    public String logoutFromApplication() {
        mouseHoverActions(profileDropDown, logout);
        Log.debug("click on logout");
        logout.click();

        Log.info("returns current url of page");
        return driver.getCurrentUrl();
    }

    /**
     * mouseHoverActions method is used to perform mouse actions
     *
     * @param firstElement  web element
     * @param secondElement web element
     */
    public void mouseHoverActions(WebElement firstElement, WebElement secondElement) {
        Actions actions = new Actions(driver);
        Log.debug("move to element" + firstElement);
        actions.moveToElement(firstElement).perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Log.debug("move to element" + secondElement);
        actions.moveToElement(secondElement).perform();
    }

    /**
     * searchProduct method is used to search for a product
     *
     * @param productName product name
     * @return title of the page
     */
    public String searchProduct(String productName) {
        Log.debug("send product name" + productName);
        searchBox.sendKeys(productName);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Log.debug("press enter");
        searchBox.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        return driver.getTitle();
    }
}
