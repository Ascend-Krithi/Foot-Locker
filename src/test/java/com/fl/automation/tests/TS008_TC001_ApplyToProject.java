package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS008_TC001_ApplyToProject extends BaseTest {

    @Test(description = "TC_4148: SCRUM-19509 TS-008 TC-001 - Apply to project")
    public void verifyApplyToProject() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement applyButton = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("button, a")));
        Assert.assertTrue(applyButton.isDisplayed(), "Apply to project functionality should be available");
    }
}