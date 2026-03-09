package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class TS004_TC3230 extends BaseTest {

    @Test
    public void verifySpecificStoreAddress() {
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
        List<WebElement> stores = driver.findElements(results);

        boolean found = false;
        for (WebElement store : stores) {
            String text = store.getText();
            if (text.contains("375 Washington Street") && text.contains("Boston") && text.contains("02108")) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Store with address 375 Washington Street, Boston, MA 02108 should be present and match exactly");
    }
}