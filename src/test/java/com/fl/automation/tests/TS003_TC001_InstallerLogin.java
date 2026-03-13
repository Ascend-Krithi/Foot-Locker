package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS003_TC001_InstallerLogin extends BaseTest {

    @Test(description = "TC_4111: SCRUM-19509 TS-003 TC-001 - Installer login")
    public void verifyInstallerLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl + "/installer/login");
        
        WebElement loginForm = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("form[class*='login'], input[type='email']")));
        Assert.assertTrue(loginForm.isDisplayed(), "Installer login form should be visible");
    }
}