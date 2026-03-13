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

public class TS011_TC001_ViewProjectDetails extends BaseTest {
    
    @Test(description = "TC4142: SCRUM-19509 TS-003 TC-001 - View project details")
    public void viewProjectDetails() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: View project details");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to marketplace");
        driver.get(MARKETPLACE_URL);
        ExtentManager.getTest().log(Status.PASS, "Marketplace loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking on first project");
        WebElement firstProject = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".project-card")));
        firstProject.click();
        ExtentManager.getTest().log(Status.PASS, "Project clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying project details page loaded");
        WebElement projectTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".project-title")));
        Assert.assertTrue(projectTitle.isDisplayed(), "Project title should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Project title displayed: " + projectTitle.getText());
        
        ExtentManager.getTest().log(Status.INFO, "Verifying project description");
        WebElement projectDescription = driver.findElement(By.cssSelector(".project-description"));
        Assert.assertTrue(projectDescription.isDisplayed(), "Project description should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Project description displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying project budget");
        WebElement projectBudget = driver.findElement(By.cssSelector(".project-budget"));
        Assert.assertTrue(projectBudget.isDisplayed(), "Project budget should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Project budget displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}