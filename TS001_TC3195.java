package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class TS001_TC3195 extends BaseTest {

    @Test
    public void searchBostonMA() {
        HomePage home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        home.clickFindAStore();

        By selectMyStore = By.xpath(
                "//*[self::a or self::button][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'select my store')]"
        );
        WebElement selectBtn = wait.until(ExpectedConditions.elementToBeClickable(selectMyStore));
        try {
            selectBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectBtn);
        }

        By locationInput = By.cssSelector("input[placeholder*='Location' i], input[type='search']");
        WebElement locBox = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
        locBox.clear();
        locBox.sendKeys("Boston, MA");

        By searchBtn = By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]");
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        try {
            searchButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
        }

        By results = By.cssSelector(".store-result, [class*='store'], [class*='location']");
        wait.until(ExpectedConditions.presenceOfElementLocated(results));
        Assert.assertTrue(driver.findElements(results).size() > 0, "Store results should be displayed for Boston, MA");
    }
}