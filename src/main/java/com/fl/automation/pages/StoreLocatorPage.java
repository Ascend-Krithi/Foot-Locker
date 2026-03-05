package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;

    private By searchInput1 = By.cssSelector("input[type='search']");
    private By searchInput2 = By.cssSelector("input[name='q']");
    private By searchInput3 = By.cssSelector("input[aria-label*='Search']");
    private By searchInput4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");

    private By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSearchInput() {
        if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInput1))) {
            return driver.findElement(searchInput1);
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInput2))) {
            return driver.findElement(searchInput2);
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInput3))) {
            return driver.findElement(searchInput3);
        } else {
            return driver.findElement(searchInput4);
        }
    }

    public void searchStore(String query) {
        BrowserUtils.type(driver, getSearchInput(), query);
        getSearchInput().submit();
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, driver.findElement(emptyResultsMsg));
    }
}
