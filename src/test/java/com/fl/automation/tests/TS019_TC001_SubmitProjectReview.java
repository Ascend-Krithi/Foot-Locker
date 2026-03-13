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

public class TS019_TC001_SubmitProjectReview extends BaseTest {
    
    @Test(description = "TC4151: SCRUM-19509 TS-011 TC-001 - Submit project review")
    public void submitProjectReview() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Submit project review");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to completed project");
        driver.get(MARKETPLACE_URL + "/project/12345/review");
        ExtentManager.getTest().log(Status.PASS, "Project review page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Selecting rating");
        WebElement fiveStarRating = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".rating-5")));
        fiveStarRating.click();
        ExtentManager.getTest().log(Status.PASS, "5-star rating selected");
        
        ExtentManager.getTest().log(Status.INFO, "Writing review");
        WebElement reviewTextarea = driver.findElement(By.id("reviewText"));
        reviewTextarea.sendKeys("Excellent work! The installer was professional, timely, and the quality of work exceeded expectations.");
        ExtentManager.getTest().log(Status.PASS, "Review text entered");
        
        ExtentManager.getTest().log(Status.INFO, "Submitting review");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Review submitted");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying review submission");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "Review submission success message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Review submitted successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}