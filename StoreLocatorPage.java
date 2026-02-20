package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[type=search]");
    private By searchInputName = By.cssSelector("input[name=q]");
    private By searchInputAria = By.cssSelector("input[aria-label*=Search]");
    private By searchInputPlaceholder = By.cssSelector("input[placeholder*=Search i], input[placeholder*=City i], input[placeholder*=ZIP i]");
    private By searchButton = By.cssSelector("button[type=submit], button[data-qa=search], button[aria-label*=Search]");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isSearchInputDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchInput) ||
                BrowserUtils.isDisplayed(driver, searchInputName) ||
                BrowserUtils.isDisplayed(driver, searchInputAria) ||
                BrowserUtils.isDisplayed(driver, searchInputPlaceholder);
    }

    public void enterSearch(String value) {
        if (BrowserUtils.isDisplayed(driver, searchInput)) {
            BrowserUtils.type(driver, searchInput, value);
        } else if (BrowserUtils.isDisplayed(driver, searchInputName)) {
            BrowserUtils.type(driver, searchInputName, value);
        } else if (BrowserUtils.isDisplayed(driver, searchInputAria)) {
            BrowserUtils.type(driver, searchInputAria, value);
        } else {
            BrowserUtils.type(driver, searchInputPlaceholder, value);
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }
}
