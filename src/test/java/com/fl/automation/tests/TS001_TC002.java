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

public class TS001_TC002 extends BaseTest {

    @Test
    public void validateStoreLocatorTextbox() {
        HomePage home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Step 1 — Click "Find a Store"
        home.clickFindAStore();

        // Step 2 — Click "Select My Store" (robust locator & click)
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

        // Step 3 — Wait for locator page/panel (handles: navigation, modal, or iframe)
        waitForStoreLocatorToBeReady(wait);

        // Step 4 — Find a robust "Location" input (several common patterns)
        By[] locationCandidates = new By[] {
                // typical placeholders/aria
                By.cssSelector("input[placeholder*='Location' i]"),
                By.cssSelector("input[aria-label*='Location' i]"),
                // city/state/zip variants
                By.cssSelector("input[placeholder*='city' i]"),
                By.cssSelector("input[placeholder*='zip' i]"),
                // generic search fields used by store locator widgets
                By.cssSelector("input[type='search']"),
                // name/id fallback patterns
                By.cssSelector("input[name*='location' i]"),
                By.cssSelector("input[id*='location' i]")
        };

        WebElement locationInput = waitForFirstVisible(wait, locationCandidates);
        Assert.assertNotNull(locationInput, "Location search textbox is NOT visible.");
        Assert.assertTrue(locationInput.isDisplayed(), "Location search textbox is NOT displayed.");

        // Step 5 — "Search for store" button (accept several text/aria variants)
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

    /**
     * Waits until the Store Locator UI is ready by trying in this order:
     *  1) URL contains 'store' or 'store-locator'
     *  2) A known input is visible in the current context
     *  3) If not, switch into any iframe that contains the input
     */
    private void waitForStoreLocatorToBeReady(WebDriverWait wait) {
        // 1) URL change to store-locator page (best case)
        try {
            wait.until((Function<WebDriver, Boolean>) d -> {
                String u = d.getCurrentUrl();
                return u != null && (u.toLowerCase().contains("store") || u.toLowerCase().contains("locator"));
            });
        } catch (TimeoutException ignored) {
            // Not all flows navigate; some open a modal.
        }

        // 2) Try to detect the input right away in current context
        if (hasLocationFieldInCurrentContext(wait)) return;

        // 3) If not found, iterate iframes and switch into the one that contains the input
        List<WebElement> iframes = driver.findElements(By.cssSelector("iframe, frame"));
        for (WebElement frame : iframes) {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(frame);
                if (hasLocationFieldInCurrentContext(wait)) {
                    return;
                }
            } catch (NoSuchFrameException | StaleElementReferenceException ignored) {
                // try next frame
            }
        }

        // Return to default content at end
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
                // try next
            }
        }
        return null;
    }
}
