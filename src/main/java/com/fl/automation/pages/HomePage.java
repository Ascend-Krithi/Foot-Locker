package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    private By findAStoreLink = By.linkText("Find a Store");
    private By findAStoreLinkCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findAStoreLinkXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        WebElement element = null;
        try {
            element = driver.findElement(findAStoreLink);
        } catch (Exception e1) {
            try {
                element = driver.findElement(findAStoreLinkCss);
            } catch (Exception e2) {
                element = driver.findElement(findAStoreLinkXpath);
            }
        }
        BrowserUtils.click(driver, element);
    }

    public boolean isFindAStoreDisplayed() {
        WebElement element = null;
        try {
            element = driver.findElement(findAStoreLink);
        } catch (Exception e1) {
            try {
                element = driver.findElement(findAStoreLinkCss);
            } catch (Exception e2) {
                element = driver.findElement(findAStoreLinkXpath);
            }
        }
        return BrowserUtils.isDisplayed(driver, element);
    }
}