package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.WaitForElement;

public class FishListPage extends BasePage{

    @FindBy(css = "tr:nth-child(2) a")
    private WebElement angelfishIdLink;

    public AngelfishListPage clickOnAngelfishId() {
        WaitForElement.waitUntilElementIsVisible(angelfishIdLink);
        angelfishIdLink.click();
        log().info("Clicked on angel fish link");
        return new AngelfishListPage();
    }
}