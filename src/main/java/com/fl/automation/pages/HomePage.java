package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;
    private By findAStoreLink = By.linkText("Find a Store");
    private By findAStoreCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findAStoreXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        WebElement link = null;
        if (driver.findElements(findAStoreLink).size() > 0) {
            link = driver.findElement(findAStoreLink);
        } else if (driver.findElements(findAStoreCss).size() > 0) {
            link = driver.findElement(findAStoreCss);
        } else if (driver.findElements(findAStoreXpath).size() > 0) {
            link = driver.findElement(findAStoreXpath);
        }
        if (link != null) {
            BrowserUtils.click(driver, link);
        } else {
            throw new RuntimeException("Find a Store link not found");
        }
    }
}