package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ConfigReader;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    private final By logInOption = By.id("login2");
    private By usernameField = By.id("loginusername");
    private By passwordField = By.id("loginpassword");
    private By loginButton = By.xpath("//button[text()='Log in']");
    private By nameOfUser = By.id("nameofuser");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to launch URL
    public void launchURL(String url) {
        driver.get(url);
    }

    // Method to perform login action
    public void logIn() throws InterruptedException {
        driver.findElement(logInOption).click();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(ConfigReader.getProperty("Username"));
        driver.findElement(passwordField).sendKeys(ConfigReader.getProperty("Password"));
        driver.findElement(loginButton).click();

        WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(nameOfUser));
        WebElement welcomeUser = driver.findElement(nameOfUser);
        String actualWelcomeText = welcomeUser.getText();
        System.out.println("Login Successful: " + actualWelcomeText);
        Assert.assertEquals(actualWelcomeText, "Welcome " + ConfigReader.getProperty("Username"));
    }
}
