package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public WebElement getFindAStoreLink() {
        for (By locator : KBLocators.FIND_A_STORE) {
            try {
                return WaitUtils.waitForDisplayed(driver, locator, 15);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Find a Store link not found");
    }

    public void clickFindAStore() {
        getFindAStoreLink().click();
    }
}