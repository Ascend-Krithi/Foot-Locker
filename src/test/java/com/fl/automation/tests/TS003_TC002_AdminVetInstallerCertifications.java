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

public class TS003_TC002_AdminVetInstallerCertifications extends BaseTest {
    
    @Test(description = "TC4109: SCRUM-19509 TS-002 TC-002 - Admin vet installer certifications")
    public void adminVetInstallerCertifications() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Admin vet installer certifications");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to admin portal");
        driver.get(ECO_HOME_HUB_URL + "/admin/installer-certifications");
        ExtentManager.getTest().log(Status.PASS, "Installer certifications page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Viewing installer certifications");
        List<WebElement> certifications = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".certification-item")));
        Assert.assertTrue(certifications.size() > 0, "Certifications should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Found " + certifications.size() + " certifications");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying first certification");
        WebElement firstCert = certifications.get(0);
        WebElement verifyBtn = firstCert.findElement(By.cssSelector(".verify-btn"));
        verifyBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Certification verification initiated");
        
        ExtentManager.getTest().log(Status.INFO, "Confirming certification");
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".confirm-verify-btn")));
        confirmBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Certification confirmed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying success message");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Certification vetted successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}