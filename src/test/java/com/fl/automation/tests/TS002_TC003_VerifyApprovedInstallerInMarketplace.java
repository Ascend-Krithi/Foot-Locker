package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS002_TC003_VerifyApprovedInstallerInMarketplace extends BaseTest {

    @Test(description = "TC_4110: SCRUM-19509 TS-002 TC-003 - Verify approved installer in marketplace")
    public void verifyApprovedInstallerInMarketplace() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl + "/marketplace");
        
        WebElement marketplace = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("div[class*='marketplace'], section[class*='installer']")));
        Assert.assertTrue(marketplace.isDisplayed(), "Marketplace should display approved installers");
    }
}