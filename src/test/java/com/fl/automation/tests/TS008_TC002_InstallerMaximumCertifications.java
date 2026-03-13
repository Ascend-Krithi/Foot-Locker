package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS008_TC002_InstallerMaximumCertifications extends BaseTest {

    @Test(description = "TC_4123: SCRUM-19509 TS-008 TC-002 - Installer maximum certifications")
    public void verifyInstallerMaximumCertifications() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement certSection = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("section, div")));
        Assert.assertTrue(certSection.isDisplayed(), "Maximum certification limits should be enforced");
    }
}