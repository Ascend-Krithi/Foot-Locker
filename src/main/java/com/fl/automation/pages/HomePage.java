package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.utils.BrowserUtils;

public class HomePage {
    private WebDriver driver;
    private By findStoreLink = By.linkText("Find a Store");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        WebElement findStore = driver.findElement(findStoreLink);
        BrowserUtils.click(driver, findStore);
    }
}