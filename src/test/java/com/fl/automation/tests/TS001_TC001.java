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

public class TS001_TC001 extends BaseTest {

    /**
     * Flow:
     * 1) Go to Home page (BaseTest opens it)
     * 2) Click "Find a Store"
     * 3) Verify text: "Choose a preferred store to make shopping easier."
     * 4) Click "Select My Store"
     */
    @Test
    public void validateFindStorePopup() {

        HomePage home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Step 1/2 — Opened by BaseTest; now click "Find a Store"
        home.clickFindAStore();

        // Step 3 — Verify the message text
        By message = By.xpath(
            "//*[contains(normalize-space(.),'Choose a preferred store to make shopping easier.') " +
            "   or contains(normalize-space(.),'Choose a preferred store')]"
        );
        WebElement textEl = wait.until(ExpectedConditions.visibilityOfElementLocated(message));
        String actual = textEl.getText();
        Assert.assertTrue(actual != null && !actual.isBlank(), "Expected the preferred store message to be visible.");
        Assert.assertTrue(
            actual.toLowerCase().contains("choose a preferred store") &&
            actual.toLowerCase().contains("shopping easier"),
            "Expected text 'Choose a preferred store to make shopping easier.' but got: " + actual
        );

        // Step 4 — Click "Select My Store"
        By selectMyStore = By.xpath(
            "//*[self::a or self::button]" +
            "[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'select my store')]"
        );
        WebElement selectBtn = wait.until(ExpectedConditions.elementToBeClickable(selectMyStore));
        selectBtn.click();
    }
}
   
