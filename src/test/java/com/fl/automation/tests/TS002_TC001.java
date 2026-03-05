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

public class TS002_TC001 extends BaseTest {

    @Test
    public void validateBostonErrorMessages() {

        HomePage homePage = new HomePage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Stabilize landing
        homePage.acceptCookiesIfPresent();
        homePage.closeFlxRewardsIfPresent();

        // Click Find a Store
        homePage.clickFindAStore();

        // Wait for popup
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Choose a preferred store')]")
        ));

        // Click "Select my store"
        WebElement selectStore = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Select my store')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectStore);

        // Wait for location textbox
        WebElement locationTextbox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[contains(@placeholder,'address') or contains(@placeholder,'Zip')]")
                )
        );

        locationTextbox.clear();
        locationTextbox.sendKeys("Boston, MA");

        // Click Search
        WebElement searchButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Search')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);

        // Wait for results dialog
        WebElement resultsDialog = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='dialog']")
                )
        );

        String popupText = resultsDialog.getText();

        System.out.println("Popup Text:\n" + popupText);

        // Validate error message 1
        Assert.assertTrue(
                popupText.contains("An error has occurred"),
                "Expected message 'An error has occurred. Please try again' was not displayed."
        );

        // Validate error message 2
        Assert.assertTrue(
                popupText.contains("Sorry, we did not find any stores"),
                "Expected 'Sorry, we did not find any stores within a 100 mile radius' message was not displayed."
        );
    }
}