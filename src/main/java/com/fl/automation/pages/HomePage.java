package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;

    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderAlt = By.cssSelector("header a[href*=stores.footlocker.com]");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void launchHomePage(String url) {
        driver.get(url);
    }

    public void clickFindStore() {
        WebElement element = null;
        if (BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeader))) {
            element = driver.findElement(findStoreHeader);
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeaderAlt))) {
            element = driver.findElement(findStoreHeaderAlt);
        } else {
            element = driver.findElement(findStoreHeaderXpath);
        }
        BrowserUtils.click(driver, element);
    }
}
