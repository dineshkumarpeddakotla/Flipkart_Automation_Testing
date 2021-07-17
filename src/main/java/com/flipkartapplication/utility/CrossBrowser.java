/*
 *Purpose : Class is implemented to use the multiple browsers by giving browser name
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 15-07-2021
 */

package com.flipkartapplication.utility;

import com.flipkartapplication.base.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class CrossBrowser extends BaseClass {

    /**
     * selectDriver method is used to select the driver value from browsers
     * @param browser browser name is given as parameter
     * @return driver value
     */
    public static WebDriver selectDriver(String browser) {

        switch (browser) {
            case "chrome": //chrome driver
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox"://firefox driver
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge"://edge driver
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "opera"://opera driver
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;
            default: System.out.println("Please provide browser name");
        }

        return driver;
    }
}
