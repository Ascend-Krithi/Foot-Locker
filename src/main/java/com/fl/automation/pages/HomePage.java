package com.fl.automation.pages;

import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    private By storeLocatorLink = By.id("store-locator-link");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickStoreLocator() {
        WebElement link = driver.findElement(storeLocatorLink);
        link.click();
    }
}
