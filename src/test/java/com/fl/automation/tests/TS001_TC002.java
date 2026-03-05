package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC002 extends BaseTest {

    @Test
    public void validateStoreLocatorTextbox() {

        HomePage homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Step 1 — Click Find a Store
        homePage.clickFindAStore();

        // Step 2 — Click "Select my store"
        By selectMyStore = By.xpath("//*[contains(text(),'Select my store') or contains(text(),'Select My Store')]");
        WebElement selectStoreBtn = wait.until(ExpectedConditions.elementToBeClickable(selectMyStore));
        selectStoreBtn.click();

        // Step 3 — Validate "Location" input textbox
        By locationTextbox = By.xpath("//input[contains(@placeholder,'Location') or " +
                                      "contains(@placeholder,'address') or contains(@placeholder,'Zip')]");
        WebElement locationInput = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox));

        Assert.assertTrue(locationInput.isDisplayed(), "Location search textbox is NOT visible.");

        // Step 4 — Validate 'Search for store' button exists
        By searchForStore = By.xpath("//*[contains(text(),'Search for store') or contains(text(),'Search Store')]");
        WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(searchForStore));

        Assert.assertTrue(searchBtn.isDisplayed(), "'Search for store' button is NOT visible.");
    }
}
