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

    /**
     * Flow:
     * 1) Go to Home page (BaseTest opens it)
     * 2) Click "Find a Store"
     * 3) Click "Select My Store"
     * 4) Verify "Location" search input and "Search for store" button are visible
     */
    @Test
    public void validateStoreLocatorTextbox() {

        HomePage home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Step 1/2 — Opened by BaseTest; now click "Find a Store"
        home.clickFindAStore();

        // Step 3 — Click "Select My Store"
        By selectMyStore = By.xpath(
            "//*[self::a or self::button]" +
            "[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'select my store')]"
        );
        WebElement selectBtn = wait.until(ExpectedConditions.elementToBeClickable(selectMyStore));
        selectBtn.click();

        // Step 4 — Validate "Location" textbox
        By locationTextbox = By.xpath(
            "//input[" +
            " contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'location') " +
            " or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'location') " +
            " or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'zip')" +
            " or contains(translate(@name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'location')" +
            "]"
        );
        WebElement locationInput = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox));
        Assert.assertTrue(locationInput.isDisplayed(), "Location search textbox is NOT visible.");

        // Step 4b — Validate "Search for store" button
        By searchForStore = By.xpath(
            "//*[self::button or self::a]" +
            "[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store') " +
            " or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"
        );
        WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(searchForStore));
        Assert.assertTrue(searchBtn.isDisplayed(), "'Search for store' button is NOT visible.");
    }
}
