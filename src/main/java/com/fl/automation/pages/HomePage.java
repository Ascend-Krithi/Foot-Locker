package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;

    private final By findAStoreLink = By.linkText("Find a Store");
    private final By findAStoreHeaderCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private final By findAStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        WebElement link = null;
        if (driver.findElements(findAStoreLink).size() > 0) {
            link = driver.findElement(findAStoreLink);
        } else if (driver.findElements(findAStoreHeaderCss).size() > 0) {
            link = driver.findElement(findAStoreHeaderCss);
        } else if (driver.findElements(findAStoreHeaderXpath).size() > 0) {
            link = driver.findElement(findAStoreHeaderXpath);
        }
        if (link != null) {
            BrowserUtils.click(driver, link);
        } else {
            throw new RuntimeException("Find a Store link not found");
        }
    }
}
