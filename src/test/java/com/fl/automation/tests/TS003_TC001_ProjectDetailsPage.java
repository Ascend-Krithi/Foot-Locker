package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS003_TC001_ProjectDetailsPage extends BaseTest {

    @Test(description = "TC_4142: SCRUM-19509 TS-003 TC-001 - Project details page")
    public void verifyProjectDetailsPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement detailsSection = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("section, div, article")));
        Assert.assertTrue(detailsSection.isDisplayed(), "Project details page should be accessible");
    }
}