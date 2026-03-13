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

public class TS004_TC002_InstallerViewProjectLeads extends BaseTest {
    
    @Test(description = "TC4112: SCRUM-19509 TS-003 TC-002 - Installer view project leads")
    public void installerViewProjectLeads() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Installer view project leads");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to installer dashboard");
        driver.get(ECO_HOME_HUB_URL + "/installer/dashboard");
        ExtentManager.getTest().log(Status.PASS, "Installer dashboard loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking on Project Leads tab");
        WebElement projectLeadsTab = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Project Leads")));
        projectLeadsTab.click();
        ExtentManager.getTest().log(Status.PASS, "Project Leads tab opened");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying project leads are displayed");
        List<WebElement> projectLeads = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".project-lead-card")));
        Assert.assertTrue(projectLeads.size() > 0, "Project leads should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Found " + projectLeads.size() + " project leads");
        
        ExtentManager.getTest().log(Status.INFO, "Viewing first project lead details");
        WebElement firstLead = projectLeads.get(0);
        WebElement viewDetailsBtn = firstLead.findElement(By.cssSelector(".view-details-btn"));
        viewDetailsBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Project lead details opened");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying lead details are displayed");
        WebElement leadDetails = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".lead-details")));
        Assert.assertTrue(leadDetails.isDisplayed(), "Lead details should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Project lead details verified");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}