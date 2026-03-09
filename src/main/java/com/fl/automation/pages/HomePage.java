package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        try {
            link = driver.findElement(findAStoreLink);
        } catch (Exception e1) {
            try {
                link = driver.findElement(findAStoreCss);
            } catch (Exception e2) {
                link = driver.findElement(findAStoreXpath);
            }
        }
        WaitUtils.waitForElementClickable(driver, link);
        BrowserUtils.click(link);
    }
}
