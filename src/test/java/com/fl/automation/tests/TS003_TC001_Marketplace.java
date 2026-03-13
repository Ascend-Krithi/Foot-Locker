package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS003_TC001_Marketplace extends BaseTest {
    @Test
    public void testProjectDetailsPage() {
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        boolean imagesVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".project-images"))).isDisplayed();
        Assert.assertTrue(imagesVisible, "Project images should be visible");
        boolean descriptionVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".project-description"))).isDisplayed();
        Assert.assertTrue(descriptionVisible, "Project description should be visible");
        boolean pricingVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".project-pricing"))).isDisplayed();
        Assert.assertTrue(pricingVisible, "Project pricing should be visible");
        boolean certificationsVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".eco-certifications"))).isDisplayed();
        Assert.assertTrue(certificationsVisible, "Eco-certifications should be visible");
    }
}