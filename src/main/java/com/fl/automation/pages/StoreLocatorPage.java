package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private By selectMyStoreXpath1 = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreXpath2 = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        WebElement link = null;
        if (WaitUtils.waitForElementDisplayed(driver, driver.findElement(selectMyStoreXpath1))) {
            link = driver.findElement(selectMyStoreXpath1);
        } else if (WaitUtils.waitForElementDisplayed(driver, driver.findElement(selectMyStoreXpath2))) {
            link = driver.findElement(selectMyStoreXpath2);
        }
        if (link != null) {
            BrowserUtils.click(link);
        } else {
            throw new RuntimeException("Select My Store link/button not found");
        }
    }
}