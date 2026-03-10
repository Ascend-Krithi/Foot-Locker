package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By[] findStoreLocators = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private By[] selectMyStoreLocators = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private By[] searchInputLocators = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void clickFindAStore() {
        WebElement element = waitForFirstVisible(findStoreLocators);
        if (element != null) {
            clickElement(element);
        }
    }

    public void clickSelectMyStore() {
        WebElement element = waitForFirstVisible(selectMyStoreLocators);
        if (element != null) {
            clickElement(element);
        }
    }

    public WebElement getSearchInput() {
        return waitForFirstVisible(searchInputLocators);
    }

    public void enterLocation(String location) {
        WebElement input = getSearchInput();
        if (input != null) {
            input.clear();
            input.sendKeys(location);
        }
    }

    public void clickSearchButton() {
        By[] searchButtonLocators = new By[] {
            By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
            By.cssSelector("[aria-label*='Search for store' i]"),
            By.cssSelector("button[type='submit']"),
            By.cssSelector("button[aria-label*='Search' i]"),
            By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]")
        };
        WebElement element = waitForFirstVisible(searchButtonLocators);
        if (element != null) {
            clickElement(element);
        }
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

    private WebElement waitForFirstVisible(By[] candidates) {
        for (By by : candidates) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}