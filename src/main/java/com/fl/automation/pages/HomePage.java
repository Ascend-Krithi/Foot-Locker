package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.WaitUtils;

public class HomePage {
    private WebDriver driver;
    private By storeLocatorLink = By.id("store-locator-link");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickStoreLocator() {
        WebElement locator = driver.findElement(storeLocatorLink);
        WaitUtils.waitForClickable(driver, locator, 10);
        locator.click();
    }
}
