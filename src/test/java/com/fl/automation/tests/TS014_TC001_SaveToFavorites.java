package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS014_TC001_SaveToFavorites extends BaseTest {

    @Test(description = "TC_4155: SCRUM-19509 TS-014 TC-001 - Save to favorites")
    public void verifySaveToFavorites() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement favoriteButton = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("button[class*='favorite'], button[class*='wishlist']")));
        Assert.assertTrue(favoriteButton.isDisplayed(), "Save to favorites functionality should be available");
    }
}