package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;
    private By searchInput1 = By.cssSelector("input[type='search']");
    private By searchInput2 = By.cssSelector("input[name='q']");
    private By searchInput3 = By.cssSelector("input[aria-label*='Search']");
    private By searchInput4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.cssSelector("button[type='submit'], button[aria-label*='Search']");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputVisible() {
        return BrowserUtils.isDisplayed(driver.findElement(searchInput1)) ||
               BrowserUtils.isDisplayed(driver.findElement(searchInput2)) ||
               BrowserUtils.isDisplayed(driver.findElement(searchInput3)) ||
               BrowserUtils.isDisplayed(driver.findElement(searchInput4));
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver.findElement(searchInput1))) {
            BrowserUtils.type(driver.findElement(searchInput1), location);
        } else if (BrowserUtils.isDisplayed(driver.findElement(searchInput2))) {
            BrowserUtils.type(driver.findElement(searchInput2), location);
        } else if (BrowserUtils.isDisplayed(driver.findElement(searchInput3))) {
            BrowserUtils.type(driver.findElement(searchInput3), location);
        } else {
            BrowserUtils.type(driver.findElement(searchInput4), location);
        }
    }

    public void clickSearch() {
        BrowserUtils.click(driver.findElement(searchButton));
    }
}
