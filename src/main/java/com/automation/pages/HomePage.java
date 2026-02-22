package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getFindAStoreButton() {
        try {
            return driver.findElement(By.linkText("Find a Store"));
        } catch (Exception e1) {
            try {
                return driver.findElement(By.cssSelector("header a[href*='stores.footlocker.com']"));
            } catch (Exception e2) {
                return driver.findElement(By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]"));
            }
        }
    }

    public void clickFindAStore() {
        getFindAStoreButton().click();
    }
}
