package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private By selectMyStoreA = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreBtn = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        WebElement btn = null;
        if (driver.findElements(selectMyStoreA).size() > 0) {
            btn = driver.findElement(selectMyStoreA);
        } else if (driver.findElements(selectMyStoreBtn).size() > 0) {
            btn = driver.findElement(selectMyStoreBtn);
        }
        if (btn != null) {
            BrowserUtils.click(driver, btn);
        } else {
            throw new RuntimeException("Select My Store button not found");
        }
    }
}