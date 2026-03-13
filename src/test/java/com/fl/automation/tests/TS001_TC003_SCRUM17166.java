package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS001_TC003_SCRUM17166 extends BaseTest {
    @Test
    public void testStoreSearch() {
        driver.get("https://www.footlocker.com");
        HomePage homePage = new HomePage(driver);
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        storeLocatorHelper.enterLocation("Boston, MA");
        storeLocatorHelper.clickSearchButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        boolean resultsDisplayed = wait.until(ExpectedConditions.or(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-qa='location']")),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector(".store-result")),
            ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'store')]"))
        ));
        Assert.assertTrue(resultsDisplayed, "Search results should be displayed");
    }
}