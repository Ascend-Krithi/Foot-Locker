package com.fl.automation.pages;

import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By storeLocatorLink = By.id("store-locator-link");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void open() {
        driver.get("https://www.example.com");
    }

    public void clickStoreLocator() {
        driver.findElement(storeLocatorLink).click();
    }
}
