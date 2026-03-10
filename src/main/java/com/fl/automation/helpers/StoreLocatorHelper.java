package com.fl.automation.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class StoreLocatorHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void waitForStoreLocatorToBeReady() {
        try {
            wait.until((Function<WebDriver, Boolean>) d -> {
                String u = d.getCurrentUrl();
                return u != null && (u.toLowerCase().contains("store") || u.toLowerCase().contains("locator"));
            });
        } catch (TimeoutException ignored) {
        }

        if (hasLocationFieldInCurrentContext()) return;

        List<WebElement> iframes = driver.findElements(By.cssSelector("iframe, frame"));
        for (WebElement frame : iframes) {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(frame);
                if (hasLocationFieldInCurrentContext()) {
                    return;
                }
            } catch (NoSuchFrameException | StaleElementReferenceException ignored) {
            }
        }

        driver.switchTo().defaultContent();
    }

    private boolean hasLocationFieldInCurrentContext() {
        try {
            By[] quickCheck = new By[]{
                    By.cssSelector("input[type='search']"),
                    By.cssSelector("input[name='q']"),
                    By.cssSelector("input[aria-label*='Search']"),
                    By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
            };
            WebElement el = waitForFirstVisible(quickCheck);
            return el != null;
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement waitForFirstVisible(By[] candidates) {
        for (By by : candidates) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch (TimeoutException ignored) {
            }
        }
        return null;
    }

    public void clickWithFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}