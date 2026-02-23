package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private final WebDriver driver;
    private final By locationInput = By.cssSelector("input[type='search']");
    private final By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isLocationInputDisplayed() {
        return BrowserUtils.isDisplayed(driver, locationInput);
    }

    public void enterLocation(String location) {
        BrowserUtils.type(driver, locationInput, location);
    }

    public boolean isSearchButtonEnabled() {
        return driver.findElement(searchButton).isEnabled();
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }
}
