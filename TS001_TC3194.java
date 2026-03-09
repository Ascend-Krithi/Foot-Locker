package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class TS001_TC3194 extends BaseTest {

    @Test
    public void validateStoreLocatorTextbox() {
        HomePage home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        home.clickFindAStore();

        By selectMyStore = By.xpath(
                "//*[self::a or self::button]" +
                "[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'select my store')]"
        );
        WebElement selectBtn = wait.until(ExpectedConditions.elementToBeClickable(selectMyStore));
        try {
            selectBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectBtn);
        }

        waitForStoreLocatorToBeReady(wait);

        By[] locationCandidates = new By[] {
                By.cssSelector("input[placeholder*='Location' i]"),
                By.cssSelector("input[aria-label*='Location' i]"),
                By.cssSelector("input[placeholder*='city' i]"),
                By.cssSelector("input[placeholder*='zip' i]"),
                By.cssSelector("input[type='search']"),
                By.cssSelector("input[name*='location' i]"),
                By.cssSelector("input[id*='location' i]")
        };

        WebElement locationInput = waitForFirstVisible(wait, locationCandidates);
        Assert.assertNotNull(locationInput, "Location search textbox is NOT visible.");
        Assert.assertTrue(locationInput.isDisplayed(), "Location search textbox is NOT displayed.");

        By[] searchBtnCandidates = new By[] {
                By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
                By.cssSelector("[aria-label*='Search for store' i]"),
                By.cssSelector("button[type='submit']"),
                By.cssSelector("button[aria-label*='Search' i]"),
                By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]")
        };

        WebElement searchBtn = waitForFirstVisible(wait, searchBtnCandidates);
        Assert.assertNotNull(searchBtn, "'Search for store' button is NOT visible.");
        Assert.assertTrue(searchBtn.isDisplayed(), "'Search for store' button is NOT displayed.");
    }

    private void waitForStoreLocatorToBeReady(WebDriverWait wait) {
        try {
            wait.until((Function<WebDriver, Boolean>) d -> {
                String u = d.getCurrentUrl();
                return u != null && (u.toLowerCase().contains("store") || u.toLowerCase().contains("locator"));
            });
        } catch (TimeoutException ignored) {
        }

        if (hasLocationFieldInCurrentContext(wait)) return;

        List<WebElement> iframes = driver.findElements(By.cssSelector("iframe, frame"));
        for (WebElement frame : iframes) {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(frame);
                if (hasLocationFieldInCurrentContext(wait)) {
                    return;
                }
            } catch (NoSuchFrameException | StaleElementReferenceException ignored) {
            }
        }

        driver.switchTo().defaultContent();
    }

    private boolean hasLocationFieldInCurrentContext(WebDriverWait wait) {
        try {
            By[] quickCheck = new By[] {
                    By.cssSelector("input[placeholder*='Location' i]"),
                    By.cssSelector("input[aria-label*='Location' i]"),
                    By.cssSelector("input[type='search']"),
                    By.cssSelector("input[placeholder*='city' i]"),
                    By.cssSelector("input[placeholder*='zip' i]")
            };
            WebElement el = waitForFirstVisible(wait, quickCheck);
            return el != null;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement waitForFirstVisible(WebDriverWait wait, By[] candidates) {
        for (By by : candidates) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch (TimeoutException ignored) {
            }
        }
        return null;
    }
}