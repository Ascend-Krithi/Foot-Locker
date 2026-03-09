package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private By locationTextbox = By.id("store-location-input");
    private By searchBtn = By.id("store-search-btn");
    private By resultsContainer = By.id("store-results-container");
    private By validationMsg = By.id("store-location-validation");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterLocation(String location) {
        WebElement textbox = driver.findElement(locationTextbox);
        textbox.clear();
        textbox.sendKeys(location);
    }

    public void clickSearch() {
        driver.findElement(searchBtn).click();
    }

    public boolean isResultsDisplayed() {
        return driver.findElements(resultsContainer).size() > 0 && driver.findElement(resultsContainer).isDisplayed();
    }

    public boolean isValidationMessageDisplayed() {
        return driver.findElements(validationMsg).size() > 0 && driver.findElement(validationMsg).isDisplayed();
    }
}
