package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS001_TC001_SCRUM19509 extends BaseTest {
    @Test
    public void testCustomerRegistration() {
        driver.get("https://eco-home-hub.example.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register")));
        registerLink.click();
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameField.sendKeys("John Doe");
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("john.doe@email.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("StrongPassword123");
        WebElement addressField = driver.findElement(By.id("address"));
        addressField.sendKeys("123 Main St, London");
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        boolean dashboardOrConfirmation = wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("dashboard"),
            ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Registration successful')]"))
        ));
        Assert.assertTrue(dashboardOrConfirmation, "Registration should be successful");
    }
}