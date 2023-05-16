package tests;

import driver.manager.DriverManager;
import driver.manager.DriverUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import page.objects.Table;

import static navigation.ApplicationURLs.TABLE_EXAMPLE;
import static org.testng.Assert.assertEquals;


public class TableTest extends TestBase{

    @TmsLink("ID-3")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @Description("Test should check table fields")
    public void checkTableFields(){

        DriverUtils.navigateToPage(TABLE_EXAMPLE);
        Table table = new Table(DriverManager.getWebDriver(), By.id("table1"));

        int numberOfRows = table.getNumberOfRows();
        int numberOfColumns = table.getNumberOfColumns();

        int expectedNumberOfRows = 4;
        int expectedNumberOfColumns = 6;

        assertEquals(numberOfRows, expectedNumberOfRows);
        assertEquals(numberOfColumns, expectedNumberOfColumns);

        int firstRowIndex = 0;
        int lastRowIndex = numberOfRows - 1;

        WebElement lastNameFirstRow = table.getLastNameForRow(firstRowIndex);
        WebElement lastNameLastRow = table.getLastNameForRow(lastRowIndex);

        assertEquals(lastNameFirstRow.getText(), "Smith");
        assertEquals(lastNameLastRow.getText(), "Conway");

        WebElement webSiteFirstRow = table.getWebSiteForRow(firstRowIndex);
        WebElement webSiteLastRow = table.getWebSiteForRow(lastRowIndex);

        assertEquals(webSiteFirstRow.getText(), "http://www.jsmith.com");
        assertEquals(webSiteLastRow.getText(), "http://www.timconway.com");

    }


}
