package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By findAStoreLink = By.linkText("Find a Store");
    private By findAStoreLinkCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findAStoreLinkXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");
    private By popupMessage = By.xpath("//*[contains(.,'Choose a preferred store to make shopping easier')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        try {
            BrowserUtils.click(driver, findAStoreLink);
        } catch (Exception e1) {
            try {
                BrowserUtils.click(driver, findAStoreLinkCss);
            } catch (Exception e2) {
                BrowserUtils.click(driver, findAStoreLinkXpath);
            }
        }
    }

    public boolean isPopupMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessage);
    }

    public String getPopupMessageText() {
        return BrowserUtils.getText(driver, popupMessage);
    }
}