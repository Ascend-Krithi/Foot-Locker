package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private By findAStoreLink = By.linkText("Find a Store");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickFindAStore() {
        WebElement el;
        if (BrowserUtils.isDisplayed(driver, driver.findElement(findAStoreLink))) {
            el = driver.findElement(findAStoreLink);
        } else {
            el = driver.findElement(By.cssSelector("header a[href*='stores.footlocker.com']"));
        }
        BrowserUtils.click(driver, el);
    }
}
