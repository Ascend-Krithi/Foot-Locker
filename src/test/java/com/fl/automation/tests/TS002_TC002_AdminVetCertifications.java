package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS002_TC002_AdminVetCertifications extends BaseTest {

    @Test(description = "TC_4109: SCRUM-19509 TS-002 TC-002 - Admin vet certifications")
    public void verifyAdminVetCertifications() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl + "/admin/certifications");
        
        WebElement certSection = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("section[class*='certification'], div[class*='cert']")));
        Assert.assertTrue(certSection.isDisplayed(), "Certification section should be visible");
    }
}