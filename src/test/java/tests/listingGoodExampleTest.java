package tests;

import driver.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class listingGoodExampleTest extends TestBase{

    @Test
    public void listingGoodExampleTest() {

        WebDriver driver = DriverManager.getWebDriver();

        // Create list of required attr
        List<String> listOfHrefAttributes = new LinkedList<String>();
        listOfHrefAttributes.add("FISH");
        listOfHrefAttributes.add("DOGS");
        listOfHrefAttributes.add("REPTILES");
        listOfHrefAttributes.add("CATS");
        listOfHrefAttributes.add("BIRDS");

        // Sort list alphabetically
        listOfHrefAttributes.sort(String::compareToIgnoreCase);

        // Create a list of WebElements for left navigation links
        List<WebElement> leftNavigationLinksList = driver.findElements(By.xpath("//*[@id='SidebarContent']/a"));

        // Create a list of WebElements for the top navigation links
        List<WebElement> topNavigationLinksList = driver.findElements(By.xpath("//*[@id='QuickLinks']/a"));

        // Create a list of WebElements for the area under the parrot's picture
        List<WebElement> bottomNavigationLinksList = driver.findElements(By.xpath("//*[@id='MainImageContent']//area"));

        // Check the lists
        assertThatListIsCorrect(leftNavigationLinksList, listOfHrefAttributes);
        assertThatListIsCorrect(topNavigationLinksList, listOfHrefAttributes);
        assertThatListIsCorrect(bottomNavigationLinksList, listOfHrefAttributes);

    }

    // The assertThatListIsCorrect method takes two arguments:
    // The first one is the current list of WebElements
    // The second is the expected list of strings (href attribute categories)
    private void assertThatListIsCorrect(List<WebElement> actualListOfWebElements, List<String> expectedListOfStrings) {

        // Create an empty list, to which we will add texts from the list of WebElements
        List<String> listOfHrefAttributes = new LinkedList<>();

        for (WebElement leftNavigationLink : actualListOfWebElements) {

            // For each WebElement from the list, we check whether it is displayed
            assertTrue(leftNavigationLink.isDisplayed());

            // For each WebElement from the list, we get the href attribute and then split it using the split() method
            // into two parts received String. Then we take the second [1] value of the expression, i.e. CATS
            String hrefCategory = leftNavigationLink.getAttribute("href").split("&categoryId=")[1];

            // Add the obtained category name to the list
            listOfHrefAttributes.add(hrefCategory);
        }

        // Sort the list of categories - similarly as above
        listOfHrefAttributes.sort(String::compareToIgnoreCase);

        // Check if the size of the list of WebElements is the same as the size of the expected list
        assertEquals(actualListOfWebElements.size(), expectedListOfStrings.size());

        // Check if the values in the lists are the same
        assertEquals(Arrays.toString(listOfHrefAttributes.toArray()), Arrays.toString(expectedListOfStrings.toArray()));
    }

}
