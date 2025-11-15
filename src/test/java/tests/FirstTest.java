package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import utils.ConfigReader;

public class FirstTest extends BaseTest {

    BasePage basePage;

    @BeforeMethod
    public void setUpTest() {
        basePage = new BasePage(driver); // driver is initialized in BaseTest
    }

    @Test
    public void verifyHomePageTitle() throws InterruptedException {
       basePage.launchURL(ConfigReader.getProperty("baseURL"));
       basePage.logIn();
    }

}
