package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC002_ extends BaseTest {

    @Test
    public void validateStoreLocatorTextbox() {
        HomePage home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);

        home.clickFindAStore();

        WebElement selectBtn = helper.waitForFirstVisible(home.getSelectMyStoreLocators());
        Assert.assertNotNull(selectBtn, "'Select My Store' button is NOT visible.");
        try {
            selectBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectBtn);
        }

        helper.waitForStoreLocatorToBeReady();

        WebElement locationInput = helper.waitForFirstVisible(home.getSearchInputLocators());
        Assert.assertNotNull(locationInput, "Location search textbox is NOT visible.");
        Assert.assertTrue(locationInput.isDisplayed(), "Location search textbox is NOT displayed.");

        By[] searchBtnCandidates = new By[]{
                By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
                By.cssSelector("[aria-label*='Search for store' i]"),
                By.cssSelector("button[type='submit']"),
                By.cssSelector("button[aria-label*='Search' i]"),
                By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]")
        };

        WebElement searchBtn = helper.waitForFirstVisible(searchBtnCandidates);
        Assert.assertNotNull(searchBtn, "'Search for store' button is NOT visible.");
        Assert.assertTrue(searchBtn.isDisplayed(), "'Search for store' button is NOT displayed.");
    }
}