/*
 *Purpose : Class is implemented for executing the test cases of flipkart application
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 15-07-2021
 */
package com.flipkartapplication;

import com.flipkartapplication.base.BaseClass;
import com.flipkartapplication.pages.HomePage;
import com.flipkartapplication.pages.Login;
import com.flipkartapplication.pages.Products;
import com.flipkartapplication.utility.DataProvider;
import com.flipkartapplication.utility.listeners.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class TestFlipkartApplication extends BaseClass {

    @Test
    @Description("verify the emailOrMobileNumber textBox is enabled to enter the text or not")
    @Severity(SeverityLevel.BLOCKER)
    public void emailOrMobileNumberTextBoxIsEnabled() {
        Login login = new Login(driver);

        Assert.assertTrue(login.emailOrMobileNumberEnabled());
    }

    @Test(priority = 1, dependsOnMethods = "emailOrMobileNumberTextBoxIsEnabled")
    @Description("verify the password text box ix enabled to enter password or not ")
    public void passwordTextBoxIsEnabled() {
        Login login = new Login(driver);

        Assert.assertTrue(login.passwordBoxEnabled());
    }

    @Test(priority = 3,dependsOnMethods = "passwordTextBoxIsEnabled")
    @Description("verify the login button is displayed or not ")
    public void loginButtonIsDisplayed() {
        Login login = new Login(driver);

        Assert.assertTrue(login.loginButtonDisplayed());
    }
    @Test(dataProvider = "LoginDetails",dataProviderClass = DataProvider.class, priority = 4)
    @Description("User is able to login to application and add product to his cart")
    public void loginToApplication(String emailOrMobileNumber, String password, String productName) {
        Login login = new Login(driver);
        HomePage homePage =  new HomePage(driver);

        login.loginToApplication(emailOrMobileNumber, password);
        String expectedText = "Login";
        String actualText = homePage.profileDropDown.getText();
        System.out.println(actualText);

        Assert.assertNotEquals(actualText, expectedText);

        if (!actualText.equals(expectedText)) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            homePage.searchProduct(productName);

            Products products =  new Products(driver);
            String actualCartUrl = products.addProductToCart();
            String expectedCartUrl = "https://www.flipkart.com/viewcart?otracker=PP_GoToCart";
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            homePage.logoutFromApplication();

            Assert.assertEquals(actualCartUrl, expectedCartUrl);
        }
    }
}
