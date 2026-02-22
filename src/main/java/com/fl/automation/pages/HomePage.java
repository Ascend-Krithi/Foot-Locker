package com.fl.automation.pages;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.WaitUtils;
import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private By findAStoreBtn = By.id("findStoreBtn");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickFindAStore() {
        WebElement btn = WaitUtils.waitForClickable(driver.findElement(findAStoreBtn), 10);
        BrowserUtils.clickElement(btn);
    }
}
