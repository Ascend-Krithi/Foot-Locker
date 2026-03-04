package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;

    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderAlt = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void launchHomePage(String url) {
        driver.get(url);
    }

    public void clickFindStore() {
        WebElement element = null;
        try {
            element = driver.findElement(findStoreHeader);
            if (BrowserUtils.isDisplayed(driver, element)) {
                BrowserUtils.click(driver, element);
                return;
            }
        } catch (Exception e) {}
        try {
            element = driver.findElement(findStoreHeaderAlt);
            if (BrowserUtils.isDisplayed(driver, element)) {
                BrowserUtils.click(driver, element);
                return;
            }
        } catch (Exception e) {}
        element = driver.findElement(findStoreHeaderXpath);
        BrowserUtils.click(driver, element);
    }
}
