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

public class TS001_TC001_ extends BaseTest {

    @Test
    public void validateFindStorePopup() {
        HomePage home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        home.clickFindAStore();

        By message = By.xpath("//*[contains(normalize-space(.),'Choose a preferred store to make shopping easier.') or contains(normalize-space(.),'Choose a preferred store')]");
        WebElement textEl = wait.until(ExpectedConditions.visibilityOfElementLocated(message));
        String actual = textEl.getText();
        Assert.assertTrue(actual != null && !actual.isBlank(), "Expected the preferred store message to be visible.");
        Assert.assertTrue(actual.toLowerCase().contains("choose a preferred store") && actual.toLowerCase().contains("shopping easier"), "Expected text 'Choose a preferred store to make shopping easier.' but got: " + actual);

        home.clickSelectMyStore();
    }
}