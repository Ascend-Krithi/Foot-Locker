package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By[] findStoreLocators = new By[]{
            By.linkText("Find a Store"),
            By.cssSelector("header a[href*='stores.footlocker.com']"),
            By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private final By[] selectMyStoreLocators = new By[]{
            By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
            By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private final By[] searchInputLocators = new By[]{
            By.cssSelector("input[type='search']"),
            By.cssSelector("input[name='q']"),
            By.cssSelector("input[aria-label*='Search']"),
            By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private final By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public By[] getFindStoreLocators() {
        return findStoreLocators;
    }

    public By[] getSelectMyStoreLocators() {
        return selectMyStoreLocators;
    }

    public By[] getSearchInputLocators() {
        return searchInputLocators;
    }

    public By getStoreResultCards() {
        return storeResultCards;
    }

    public By getStoreAddress() {
        return storeAddress;
    }

    public By getSetMyStoreButton() {
        return setMyStoreButton;
    }

    public By getEmptyResultsMessage() {
        return emptyResultsMessage;
    }

    public void clickFindAStore() {
        WebElement findStore = waitForFirstVisible(findStoreLocators);
        if (findStore != null) {
            try {
                findStore.click();
            } catch (Exception e) {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", findStore);
            }
        }
    }

    private WebElement waitForFirstVisible(By[] candidates) {
        for (By by : candidates) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}