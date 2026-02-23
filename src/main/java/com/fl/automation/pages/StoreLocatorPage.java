package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class StoreLocatorPage {
    private WebDriver driver;
    private WaitUtils waitUtils;
    private int timeout;

    private By locationTextbox = By.id("locationInput");
    private By searchButton = By.id("searchBtn");
    private By errorMsg = By.id("locationError");

    public StoreLocatorPage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.timeout = timeout;
        this.waitUtils = new WaitUtils(driver, timeout);
    }

    public boolean isLocationTextboxDisplayed() {
        WebElement textbox = waitUtils.waitForElementToBeVisible(driver.findElement(locationTextbox));
        return textbox.isDisplayed();
    }

    public boolean isSearchButtonEnabled() {
        WebElement button = waitUtils.waitForElementToBeVisible(driver.findElement(searchButton));
        return button.isEnabled();
    }

    public StoreResultsPage searchStore(String location) {
        WebElement textbox = waitUtils.waitForElementToBeVisible(driver.findElement(locationTextbox));
        textbox.clear();
        textbox.sendKeys(location);
        waitUtils.waitForElementToBeClickable(driver.findElement(searchButton)).click();
        return new StoreResultsPage(driver, timeout);
    }

    public boolean isErrorMessageDisplayed() {
        WebElement error = waitUtils.waitForElementToBeVisible(driver.findElement(errorMsg));
        return error.isDisplayed();
    }

    public String getErrorMessageText() {
        WebElement error = waitUtils.waitForElementToBeVisible(driver.findElement(errorMsg));
        return error.getText();
    }
}