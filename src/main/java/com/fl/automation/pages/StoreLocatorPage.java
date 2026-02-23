package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private final WebDriver driver;
    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }
    public void enterSearch(String searchText) {
        WebElement el = null;
        try {
            el = driver.findElement(By.cssSelector("input[type='search']"));
        } catch (Exception ignore) {}
        if (el == null) {
            try {
                el = driver.findElement(By.cssSelector("input[name='q']"));
            } catch (Exception ignore) {}
        }
        if (el == null) {
            try {
                el = driver.findElement(By.cssSelector("input[aria-label*='Search']"));
            } catch (Exception ignore) {}
        }
        if (el == null) {
            el = driver.findElement(By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]"));
        }
        BrowserUtils.type(driver, el, searchText);
    }
    public boolean isEmptyResultsMessageDisplayed() {
        try {
            WebElement el = driver.findElement(By.xpath("//*[contains(.,'There are no locations in your search area')]"));
            return BrowserUtils.isDisplayed(driver, el);
        } catch (Exception e) {
            return false;
        }
    }
}