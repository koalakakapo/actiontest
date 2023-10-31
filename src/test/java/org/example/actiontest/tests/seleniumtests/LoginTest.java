package org.example.actiontest.tests.seleniumtests;

//import org.delta.automation.automationcoreframework.util.ApplicationProperties;
//import org.delta.automation.selenium.automationbaseclasses.BaseSeleniumTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for seleium.
 */
//public class LoginTest extends BaseSeleniumTest {
public class LoginTest{
    /**
     * The site url.
     */
    private static String siteAutomationUrl = "https://www.google.com/";

    /**
     * Test case for verifying the url matches our expectations.
     */
    @Test
    public void getBrowserTest() {
        WebDriver driver;

        //Setting system properties of ChromeDriver
        System.setProperty("webdriver.chrome.driver", "webdriver\\chromedriver.exe");

        //Creating an object of ChromeDriver
        driver = new ChromeDriver();

        //launching the specified URL
        driver.get(siteAutomationUrl);
        driver.close();
    }
}
