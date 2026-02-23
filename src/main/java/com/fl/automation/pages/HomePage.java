package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;
    private By[] findStoreHeaderLocators = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickFindAStore() {
        WebElement header = null;
        for (By locator : findStoreHeaderLocators) {
            try {
                header = driver.findElement(locator);
                if (header.isDisplayed()) {
                    BrowserUtils.click(driver, header);
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Find a Store header not found");
    }
}
