package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    // Strict locator policy for search input
    private final By searchInput = By.cssSelector("input[type=search]");
    private final By searchInputAlt1 = By.cssSelector("input[name=q]");
    private final By searchInputAlt2 = By.cssSelector("input[aria-label*=Search]");
    private final By searchInputAlt3 = By.cssSelector("input[placeholder*=Search i], input[placeholder*=City i], input[placeholder*=ZIP i]");
    private final By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");
    private final By searchButtonAlt = By.xpath("//input[@type='submit' and contains(@value,'Search for Stores')]");
    private final By locationTextbox = searchInput;

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isLocationTextboxDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchInput) ||
               BrowserUtils.isDisplayed(driver, searchInputAlt1) ||
               BrowserUtils.isDisplayed(driver, searchInputAlt2) ||
               BrowserUtils.isDisplayed(driver, searchInputAlt3);
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchButton) || BrowserUtils.isDisplayed(driver, searchButtonAlt);
    }

    public boolean isSearchButtonEnabled() {
        try {
            return DriverFactory.getDriver().findElement(searchButton).isEnabled();
        } catch (Exception e) {
            try {
                return DriverFactory.getDriver().findElement(searchButtonAlt).isEnabled();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, searchInput)) {
            BrowserUtils.type(driver, searchInput, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputAlt1)) {
            BrowserUtils.type(driver, searchInputAlt1, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputAlt2)) {
            BrowserUtils.type(driver, searchInputAlt2, location);
        } else {
            BrowserUtils.type(driver, searchInputAlt3, location);
        }
    }

    public void clickSearchForStores() {
        if (BrowserUtils.isDisplayed(driver, searchButton)) {
            BrowserUtils.click(driver, searchButton);
        } else {
            BrowserUtils.click(driver, searchButtonAlt);
        }
    }
}
