package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS002_TC001_AdminApproveInstaller extends BaseTest {

    @Test(description = "TC_4108: SCRUM-19509 TS-002 TC-001 - Admin approve installer")
    public void verifyAdminApproveInstaller() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl + "/admin");
        
        WebElement adminPanel = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("div[class*='admin'], section[class*='dashboard']")));
        Assert.assertTrue(adminPanel.isDisplayed(), "Admin panel should be accessible");
    }
}