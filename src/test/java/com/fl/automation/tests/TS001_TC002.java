package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Stabilize landing
        homePage.acceptCookiesIfPresent();
        homePage.closeFlxRewardsIfPresent();

        // Step 1 — Click Find a Store
        homePage.clickFindAStore();

        // Step 2 — Wait for popup
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Choose a preferred store')]")
        ));

        // Step 3 — Click "Select my store"
        WebElement selectStore = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Select my store')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectStore);

        // Step 4 — Wait for Location textbox
        WebElement locationTextbox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[contains(@placeholder,'address') or contains(@placeholder,'Zip')]")
                )
        );

        // Step 5 — Validate textbox displayed
        Assert.assertTrue(
                locationTextbox.isDisplayed(),
                "Location textbox should be visible in the Find a Store popup."
        );
    }
}