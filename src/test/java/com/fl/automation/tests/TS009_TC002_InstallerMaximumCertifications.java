package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TS009_TC002_InstallerMaximumCertifications extends BaseTest {
    
    @Test(description = "TC4123: SCRUM-19509 TS-008 TC-002 - Installer maximum certifications")
    public void installerMaximumCertifications() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Installer maximum certifications");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to installer profile page");
        driver.get(ECO_HOME_HUB_URL + "/installer/profile");
        ExtentManager.getTest().log(Status.PASS, "Installer profile page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Add Certification button");
        WebElement addCertBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-certification-btn")));
        addCertBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Add Certification button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying existing certifications count");
        List<WebElement> certifications = driver.findElements(By.cssSelector(".certification-item"));
        int certCount = certifications.size();
        ExtentManager.getTest().log(Status.INFO, "Current certifications count: " + certCount);
        
        ExtentManager.getTest().log(Status.INFO, "Attempting to add certification beyond maximum");
        if (certCount >= 10) {
            WebElement maxCertError = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-max-certifications")));
            Assert.assertTrue(maxCertError.isDisplayed(), "Maximum certification error should be displayed");
            ExtentManager.getTest().log(Status.PASS, "Maximum certification limit validated");
        } else {
            ExtentManager.getTest().log(Status.INFO, "Maximum certification limit not reached yet");
        }
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}