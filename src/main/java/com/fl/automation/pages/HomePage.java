package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;
    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }
    public void open() {
        driver.get(ConfigReader.get("baseUrl"));
    }
    public void clickFindAStoreHeader() {
        WebElement el = null;
        try {
            el = driver.findElement(By.linkText("Find a Store"));
        } catch (Exception ignore) {}
        if (el == null) {
            try {
                el = driver.findElement(By.cssSelector("header a[href*='stores.footlocker.com']"));
            } catch (Exception ignore) {}
        }
        if (el == null) {
            el = driver.findElement(By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]"));
        }
        BrowserUtils.click(driver, el);
    }
}