package com.fl.automation.pages;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.WaitUtils;
import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreResultsPage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[type='search']");
    private By searchButton = By.cssSelector("button[type='submit']");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        try {
            WebElement input = driver.findElement(searchInput);
            return BrowserUtils.isDisplayed(input);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement button = driver.findElement(searchButton);
            return BrowserUtils.isDisplayed(button);
        } catch (Exception e) {
            return false;
        }
    }
}