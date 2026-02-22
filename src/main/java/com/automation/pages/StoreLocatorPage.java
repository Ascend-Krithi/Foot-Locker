package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPopupContainer() {
        return driver.findElement(By.xpath("//div[contains(@class,'popover') or contains(@class,'popup') or contains(@class,'store')][.//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]]"));
    }

    private WebElement getSelectMyStoreLink() {
        try {
            return driver.findElement(By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"));
        } catch (Exception e) {
            return driver.findElement(By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"));
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            return getSelectMyStoreLink().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPopupMessage() {
        return getPopupContainer().getText();
    }
}
