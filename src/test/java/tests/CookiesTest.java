package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertTrue;


public class CookiesTest {

    private static final String LOGIN_URL = "http://przyklady.javastart.pl/jpetstore/actions/Account.action?signonForm=";
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        driver = new ChromeDriver();

        driver.navigate().to(LOGIN_URL);
    }

    @Test
    public void cookieExampleUsageTest() {

        // Get username field and fill
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.clear();
        usernameField.sendKeys("j2ee");

        // Get password field and fill
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys("j2ee");

        // Get login button and click
        WebElement loginButton = driver.findElement(By.name("signon"));
        loginButton.click();

        // Get dog banner
        WebElement bannerLogoAfterLogin = driver.findElement(By.cssSelector("#Banner img[src*='dog']"));

        // Is banner displayed
        assertTrue(bannerLogoAfterLogin.isDisplayed());

        // Get all cookies
        Set<Cookie> cookiesSet = driver.manage().getCookies();

        // Get from set first object - login cookie
        Cookie loginCookie = cookiesSet.iterator().next();

        // Delete all cookies
        driver.manage().deleteAllCookies();

        // Refresh page
        driver.navigate().refresh();

        // Now dog banner is not avaliable
        // As login cookie was deleted site does not recognize user
        // In try catch test in trying to search for banner after login element, but it is not avaliable
        try {
            WebElement bannerLogoAfterClearingCookies = driver.findElement(By.cssSelector("#Banner img[src*='dog']"));
            assertTrue(bannerLogoAfterClearingCookies.isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Element Dog banner after log in is not displayed!");
        }

        // After catching exception test adds cookkie with login data
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(loginCookie);

        //Go back to login page
        driver.navigate().refresh();

        // Dog banner is avalaible, because login cookie is set
        WebElement bannerLogoAfterAddingCookie = driver.findElement(By.cssSelector("#Banner img[src*='dog']"));
        assertTrue(bannerLogoAfterAddingCookie.isDisplayed());
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
        driver.quit();
    }

}