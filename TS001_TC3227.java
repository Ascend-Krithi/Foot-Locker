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

public class TS001_TC3227 extends BaseTest {

    @Test
    public void verifyFindAStorePopup() {
        HomePage home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        home.clickFindAStore();

        By popup = By.xpath("//*[contains(@class,'popup') or contains(@class,'modal') or contains(@id,'store')]");
        WebElement popupEl = wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
        Assert.assertTrue(popupEl.isDisplayed(), "Find a Store popup should be displayed");

        By message = By.xpath("//*[contains(normalize-space(.),'Choose a preferred store')]");
        WebElement msgEl = wait.until(ExpectedConditions.visibilityOfElementLocated(message));
        Assert.assertTrue(msgEl.getText().contains("Choose a preferred store"), "Popup should display correct message");

        By selectLink = By.xpath("//*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'select my store')]");
        WebElement linkEl = wait.until(ExpectedConditions.visibilityOfElementLocated(selectLink));
        Assert.assertTrue(linkEl.isDisplayed(), "Select My Store link should be visible");
    }
}