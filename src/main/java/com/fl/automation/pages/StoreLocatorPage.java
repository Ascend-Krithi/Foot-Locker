package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;

    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        WebElement element = null;
        if (driver.findElements(selectMyStoreLink).size() > 0) {
            element = driver.findElement(selectMyStoreLink);
        } else if (driver.findElements(selectMyStoreButton).size() > 0) {
            element = driver.findElement(selectMyStoreButton);
        }
        if (element != null) {
            BrowserUtils.click(driver, element);
        } else {
            throw new RuntimeException("Select My Store element not found");
        }
    }
}
