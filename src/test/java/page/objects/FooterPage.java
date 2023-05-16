package page.objects;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.WaitForElement;

import java.io.File;
import java.io.IOException;

import static generic.assertions.AssertWebElement.assertThat;
import static utils.ScreenShotMaker.makeScreenShot;
import static utils.ScreenShotMaker.makeScreenShotAndSaveAsFile;

public class FooterPage extends BasePage {

    @FindBy(css = "#Banner img[src*='dog']")

    private WebElement bannerAfterLoginLogo;

    @Step("Getting if dog banner is displayed")
    public boolean isBannerAfterLoginDisplayed(){
        WaitForElement.waitUntilElementIsVisible(bannerAfterLoginLogo);
        boolean isDisplayed = bannerAfterLoginLogo.isDisplayed();
        log().info("Returning status of banner after login: {}", isDisplayed);
        return isDisplayed;
    }

    @Step("Assert that element dog banner is displayed")
    public FooterPage assertThatDogBannerIsDisplayed(){
        log().info("Checking if dog banner is displayed");
        WaitForElement.waitUntilElementIsVisible(bannerAfterLoginLogo);
        assertThat(bannerAfterLoginLogo).isDisplayed();
        return this;
    }

    @Step("Take screenshot of dog banner")
    public FooterPage takeScreenchotOfDogBanner(){
        log().info("Taking screenshot of dog banner");
        makeScreenShotAndSaveAsFile();
        return this;
    }

}