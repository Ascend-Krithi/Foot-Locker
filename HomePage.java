package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;

    private By findStoreLink = By.linkText("Find a Store");
    private By findStoreLinkFallback1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreLinkFallback2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePageLoaded() {
        return driver.getTitle().contains("Foot Locker") || driver.getCurrentUrl().contains("footlocker.com");
    }

    public WebElement getFindStoreElement() {
        try {
            return driver.findElement(findStoreLink);
        } catch (Exception e1) {
            try {
                return driver.findElement(findStoreLinkFallback1);
            } catch (Exception e2) {
                return driver.findElement(findStoreLinkFallback2);
            }
        }
    }

    public void clickFindStore() {
        WebElement findStore = getFindStoreElement();
        BrowserUtils.click(driver, findStore);
    }
}
