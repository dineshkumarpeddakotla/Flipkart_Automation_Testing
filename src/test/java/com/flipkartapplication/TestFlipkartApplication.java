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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class TestFlipkartApplication extends BaseClass {

    @Test(dataProvider = "LoginDetails",dataProviderClass = DataProvider.class)
    @Description("User is able to login to application and add product to his cart")
    public void loginToApplication(String emailOrMobileNumber, String password, String productName) {
        Login login = new Login(driver);
        login.loginToApplication(emailOrMobileNumber, password);
        String actualUrl = "https://www.flipkart.com/";
        String expectedUrl = driver.getCurrentUrl();

        System.out.println(actualUrl);
        Assert.assertEquals(expectedUrl,actualUrl);

        System.out.println(!login.loginBootStrap.isDisplayed());
        System.out.println(login.loginBootStrap.isDisplayed());

        if (login.loginBootStrap.isDisplayed()) {
            HomePage homePage =  new HomePage(driver);

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
