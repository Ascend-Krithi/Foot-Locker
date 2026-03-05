package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;
    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void launch() {
        driver.get(com.fl.automation.core.ConfigReader.getProperty("baseUrl"));
    }

    public void clickFindStore() {
        WebElement el = null;
        try {
            el = driver.findElement(findStoreHeader);
            if (BrowserUtils.isDisplayed(driver, el)) {
                BrowserUtils.click(driver, el);
                return;
            }
        } catch (Exception e) {}
        try {
            el = driver.findElement(findStoreHeaderCss);
            if (BrowserUtils.isDisplayed(driver, el)) {
                BrowserUtils.click(driver, el);
                return;
            }
        } catch (Exception e) {}
        el = driver.findElement(findStoreHeaderXpath);
        BrowserUtils.click(driver, el);
    }

    public boolean isSelectMyStoreLinkPresent() {
        By selectMyStoreLink1 = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
        By selectMyStoreLink2 = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreLink1)) ||
                   BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreLink2));
        } catch (Exception e) {
            return false;
        }
    }
}
