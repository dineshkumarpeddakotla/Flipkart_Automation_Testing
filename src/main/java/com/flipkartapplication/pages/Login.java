/*
 *Purpose : Class is implemented to find the web elements in Login and  written the steps
 *                @FindBy annotation is used to find the web elements
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 15-07-2021
 */

package com.flipkartapplication.pages;

import com.flipkartapplication.base.BaseClass;
import com.flipkartapplication.utility.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Login extends BaseClass {

    @FindBy(xpath = "//input[@class = '_2IX_2- VJZDxU']")
    WebElement emailOrMobileNumber;
    @FindBy(xpath = "//input[@type='password']")
    WebElement password;
    @FindBy(xpath = "//span[contains(text(),'Forgot?')]")
    WebElement forgot;
    @FindBy(className = "_1Ijv5h")
    WebElement termsAndConditions;
    @FindBy(xpath = "//button/span[contains(text(),'Login')]")
    WebElement loginButton;
    @FindBy(xpath = "//div[contains(text(),'OR')]")
    WebElement or;
    @FindBy(xpath = "//button[contains(text(),'Request OTP')]")
    WebElement RequestOTP;
    @FindBy(linkText = "New to Flipkart? Create an account")
    WebElement createAccount;
    @FindBy(xpath = "/html/body/div[2]/div/div")
    public WebElement loginBootStrap;

    public Login(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public Boolean emailOrMobileNumberEnabled() {
        return emailOrMobileNumber.isEnabled();
    }

    public Boolean passwordBoxEnabled() {
        return password.isEnabled();
    }

    public Boolean loginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    @Step("1.Enter email or mobile number" +
            "2.Enter password" +
            "3.Click on login button")
    public String loginToApplication(String email, String pass) {
        Log.debug("clear the emailOrMobileNumber text box");
        emailOrMobileNumber.clear();
        Log.debug("email or mobile number is entered " + email);
        emailOrMobileNumber.sendKeys(email);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Log.debug("clear the password text box");
        password.clear();
        Log.debug("password is entered" + pass);
        password.sendKeys(pass);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Log.debug("click on login button");
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String a ="//a[contains(text(),'Login')]";

        return driver.getTitle();
    }
}
