package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By findAStoreHeader = By.linkText("Find a Store");
    private By findAStoreHeaderAlt1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findAStoreHeaderAlt2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickFindAStore() {
        if (BrowserUtils.isDisplayed(driver, findAStoreHeader)) {
            BrowserUtils.click(driver, findAStoreHeader);
        } else if (BrowserUtils.isDisplayed(driver, findAStoreHeaderAlt1)) {
            BrowserUtils.click(driver, findAStoreHeaderAlt1);
        } else {
            BrowserUtils.click(driver, findAStoreHeaderAlt2);
        }
    }
}
