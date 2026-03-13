package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS015_TC001_Marketplace extends BaseTest {
    @Test
    public void testMarketplaceHelpAndSupport() {
        driver.get("https://eco-renovation.com/marketplace/home");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Help"))).click();
        boolean helpPageDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".help-resources"))).isDisplayed();
        Assert.assertTrue(helpPageDisplayed, "Help and support resources should be accessible and functional");
    }
}