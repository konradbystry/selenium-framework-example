package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table {
    private WebElement table;

    private List<WebElement> rows;

    private List<WebElement> columns;

    public Table(WebDriver driver, By tableSelector){
        table = driver.findElement(tableSelector);

        columns = table.findElements(By.xpath("thead//span"));

        rows = table.findElements(By.xpath("tbody/tr"));
    }

    public int getNumberOfRows() {
        int size = rows.size();
        return size;
    }

    public int getNumberOfColumns() {
        int size = columns.size();
        return size;
    }

    public WebElement getLastNameForRow(int rowIndex) {
        WebElement lastNameRow = rows.get(rowIndex).findElement(By.xpath("td[1]"));
        return lastNameRow;
    }

    public WebElement getWebSiteForRow(int rowIndex) {
        WebElement webSiteRow = rows.get(rowIndex).findElement(By.xpath("td[5]"));
        return webSiteRow;
    }

}
