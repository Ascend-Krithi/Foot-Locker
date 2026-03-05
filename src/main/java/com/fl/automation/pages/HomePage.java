package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private final By findAStoreLink = By.linkText("Find a Store");
    private final By findAStoreLinkCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private final By findAStoreLinkXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        if (BrowserUtils.isDisplayed(driver, findAStoreLink)) {
            BrowserUtils.click(driver, findAStoreLink);
        } else if (BrowserUtils.isDisplayed(driver, findAStoreLinkCss)) {
            BrowserUtils.click(driver, findAStoreLinkCss);
        } else {
            BrowserUtils.click(driver, findAStoreLinkXpath);
        }
    }

    public boolean isFindAStorePopupDisplayed() {
        // The popup is expected after clicking Find a Store; check for Select My Store link
        By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
        return BrowserUtils.isDisplayed(driver, selectMyStoreLink);
    }

    public boolean isSelectMyStoreLinkVisible() {
        By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
        return BrowserUtils.isDisplayed(driver, selectMyStoreLink);
    }
}
