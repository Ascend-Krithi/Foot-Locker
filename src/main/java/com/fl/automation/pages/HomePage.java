package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;
    private By findStoreHeaderLink = By.linkText("Find a Store");
    private By findStoreHeaderCss = By.cssSelector("header a[href*=stores.footlocker.com]");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        WebElement link = null;
        if (driver.findElements(findStoreHeaderLink).size() > 0) {
            link = driver.findElement(findStoreHeaderLink);
        } else if (driver.findElements(findStoreHeaderCss).size() > 0) {
            link = driver.findElement(findStoreHeaderCss);
        } else if (driver.findElements(findStoreHeaderXpath).size() > 0) {
            link = driver.findElement(findStoreHeaderXpath);
        }
        if (link != null) {
            BrowserUtils.click(driver, link);
        } else {
            throw new RuntimeException("Find a Store link not found");
        }
    }
}