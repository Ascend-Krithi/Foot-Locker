package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;
    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }
    private WebElement getFindAStoreLink() {
        try {
            return driver.findElement(By.linkText("Find a Store"));
        } catch (Exception e1) {
            try {
                return driver.findElement(By.cssSelector("header a[href*='stores.footlocker.com']"));
            } catch (Exception e2) {
                return driver.findElement(By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]"));
            }
        }
    }
    public void clickFindAStore() {
        BrowserUtils.click(getFindAStoreLink(), driver);
    }
}
