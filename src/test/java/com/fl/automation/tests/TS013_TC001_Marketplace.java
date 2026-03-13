package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS013_TC001_Marketplace extends BaseTest {
    @Test
    public void testMarketplaceShareProjectEmail() {
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Share')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Email')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recipient"))).sendKeys("friend@example.com");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        boolean confirmationDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Project shared')]"))).isDisplayed();
        Assert.assertTrue(confirmationDisplayed, "Project should be shared successfully via email");
    }
}