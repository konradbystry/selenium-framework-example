package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.util.Date;
import java.util.logging.Level;

import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;

public class LogExampleTests {

    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");


        // Set loggin level
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.INFO);
        chromeOptions.setCapability("goog:loggingPrefs", loggingPreferences);
        chromeOptions.merge(chromeOptions);

        driver = new ChromeDriver(chromeOptions);

        driver.navigate().to("http://przyklady.javastart.pl/jpetstore/");
    }

    @Test
    public void browserLogExampleTest() {
        WebElement enterStoreLink = driver.findElement(By.cssSelector("#Content a"));
        enterStoreLink.click();

        WebElement signOnLink = driver.findElement(By.cssSelector("#MenuContent a[href*='signonForm']"));
        signOnLink.click();

        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("NotExistingLogin");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("NotProperPassword");

        WebElement signOnButton = driver.findElement(By.name("signon"));
        signOnButton.click();

        WebElement messageLabel = driver.findElement(By.cssSelector("#Content ul[class='messages'] li"));
        assertEquals("Invalid username or password. Signon failed.", messageLabel.getText());

        System.out.println("================== Browser logs (JavaScript Console) =======================");

        // Get log from browser level
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : browserLogs) {

            // Print log
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());

            // Check whether the entry is classified as SEVERE, i.e. an error
            assertFalse(entry.getLevel().equals(Level.SEVERE));
        }
        System.out.println("=======================================================");
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
        driver.quit();
    }
}