package com.fl.automation.pages;

import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By results = By.cssSelector(".store-result");
    private By pagination = By.cssSelector(".pagination");
    private By sortDropdown = By.id("sort-dropdown");
    private By filterCheckbox = By.cssSelector(".filter-checkbox");
    private By map = By.id("store-map");
    private By noResults = By.id("no-results");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public List<WebElement> getResults() {
        return driver.findElements(results);
    }

    public boolean isPaginationDisplayed() {
        return driver.findElements(pagination).size() > 0;
    }

    public void sortResults(String option) {
        driver.findElement(sortDropdown).sendKeys(option);
    }

    public void filterResults() {
        driver.findElement(filterCheckbox).click();
    }

    public boolean isMapDisplayed() {
        return driver.findElement(map).isDisplayed();
    }

    public boolean isNoResultsDisplayed() {
        return driver.findElements(noResults).size() > 0;
    }
}
