package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;

    private By findAStoreLinkByLinkText = By.linkText("Find a Store");
    private By findAStoreLinkByCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findAStoreLinkByXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        if (BrowserUtils.isDisplayed(driver, findAStoreLinkByLinkText)) {
            BrowserUtils.click(driver, findAStoreLinkByLinkText);
        } else if (BrowserUtils.isDisplayed(driver, findAStoreLinkByCss)) {
            BrowserUtils.click(driver, findAStoreLinkByCss);
        } else if (BrowserUtils.isDisplayed(driver, findAStoreLinkByXpath)) {
            BrowserUtils.click(driver, findAStoreLinkByXpath);
        } else {
            throw new RuntimeException("Find a Store link not found on Home Page");
        }
    }
}
