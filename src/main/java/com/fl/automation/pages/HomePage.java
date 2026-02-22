package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    // STRICT LOCATOR POLICY: Use unique, stable locators only
    private final By storeLocatorLink = By.cssSelector("a[data-test='store-locator-link']");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickStoreLocator() {
        WebElement element = driver.findElement(storeLocatorLink);
        BrowserUtils.click(driver, element);
    }
}
