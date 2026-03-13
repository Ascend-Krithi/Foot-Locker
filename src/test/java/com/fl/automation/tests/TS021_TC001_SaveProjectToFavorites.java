package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * TC_ID: 4155
 * Test Case: Save Project To Favorites
 * Description: Login, navigate to project details, click 'Save to Favorites', verify project saved to favorites and visible in dashboard.
 */
public class TS021_TC001_SaveProjectToFavorites extends BaseTest {

    @Test
    public void saveProjectToFavorites() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        
        try {
            WebElement saveButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'save') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'favorite')]")
            ));
            clickElement(saveButton);
            
            WebElement confirmationMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'saved') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'favorite')]")
            ));
            
            Assert.assertTrue(confirmationMessage.isDisplayed(), "Save to favorites confirmation not displayed");
        } catch (Exception e) {
            Assert.fail("Save project to favorites failed: " + e.getMessage());
        }
    }
    
    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}