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

public class TS001_TC001__02 extends BaseTest {

    @Test
    public void launchAppAndVerifyFindStorePopup() {
        HomePage home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        WebElement findStoreBtn = waitForFirstVisible(wait, home.getFindStoreLocators());
        Assert.assertNotNull(findStoreBtn, "'Find a Store' button is NOT visible.");
        Assert.assertTrue(findStoreBtn.isDisplayed(), "'Find a Store' button is NOT displayed.");

        home.clickFindAStore();

        By message = By.xpath(
                "//*[contains(normalize-space(.),'Choose a preferred store to make shopping easier.') " +
                        "   or contains(normalize-space(.),'Choose a preferred store')]"
        );
        WebElement textEl = wait.until(ExpectedConditions.visibilityOfElementLocated(message));
        String actual = textEl.getText();
        Assert.assertTrue(actual != null && !actual.isBlank(), "Expected the preferred store message to be visible.");

        WebElement selectBtn = waitForFirstVisible(wait, home.getSelectMyStoreLocators());
        Assert.assertNotNull(selectBtn, "'Select My Store' link is NOT visible.");
        Assert.assertTrue(selectBtn.isDisplayed(), "'Select My Store' link is NOT displayed.");
    }

    private WebElement waitForFirstVisible(WebDriverWait wait, By[] candidates) {
        for (By by : candidates) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}