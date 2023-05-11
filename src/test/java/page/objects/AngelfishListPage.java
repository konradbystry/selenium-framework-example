package page.objects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.WaitForElement;

public class AngelfishListPage extends BasePage {

    @FindBy(css = "a.Button[href$='EST-2']")
    private WebElement smallAngelfishAddToCartButton;

    public ShoppingCartPage clickOnAddToCartSmallAngelfish() {
        WaitForElement.waitUntilElementIsVisible(smallAngelfishAddToCartButton);
        log().info("Clicked on add to cart for small angel fish");

        smallAngelfishAddToCartButton.click();
        return new ShoppingCartPage();
    }
}