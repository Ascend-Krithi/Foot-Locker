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

public class TS020_TC001_ViewEcoCertifications extends BaseTest {
    
    @Test(description = "TC4152: SCRUM-19509 TS-012 TC-001 - View eco-certifications")
    public void viewEcoCertifications() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: View eco-certifications");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to installer profile");
        driver.get(MARKETPLACE_URL + "/installer/12345");
        ExtentManager.getTest().log(Status.PASS, "Installer profile page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Certifications tab");
        WebElement certificationsTab = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Certifications")));
        certificationsTab.click();
        ExtentManager.getTest().log(Status.PASS, "Certifications tab opened");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying eco-certifications are displayed");
        List<WebElement> certifications = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".certification-badge")));
        Assert.assertTrue(certifications.size() > 0, "Eco-certifications should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Found " + certifications.size() + " eco-certifications");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying certification details");
        WebElement firstCert = certifications.get(0);
        firstCert.click();
        
        WebElement certDetails = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cert-details")));
        Assert.assertTrue(certDetails.isDisplayed(), "Certification details should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Certification details verified");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}