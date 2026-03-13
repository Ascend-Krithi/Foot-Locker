package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private static final By COOKIE_ACCEPT_1 = By.id("onetrust-accept-btn-handler");
    private static final By COOKIE_ACCEPT_2 = By.cssSelector("button#onetrust-accept-btn-handler");
    private static final By COOKIE_ACCEPT_3 = By.cssSelector("button[aria-label*='Accept' i]");
    private static final By COOKIE_ACCEPT_4 = By.xpath("//button[contains(normalize-space(.),'Accept All Cookies')]");

    private static final By MODAL_CLOSE_1 = By.cssSelector("button[aria-label='Close']");
    private static final By MODAL_CLOSE_2 = By.xpath("//button[@aria-label='Close' or contains(@class,'close')]");

    private static final By HEADER_FIND_A_STORE_1 = By.linkText("Find a Store");
    private static final By HEADER_FIND_A_STORE_2 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private static final By HEADER_FIND_A_STORE_3 = By.xpath("//header//a[contains(.,'Find a Store')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.js = (JavascriptExecutor) driver;
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public void acceptCookies() {
        try {
            WebElement cookieButton = findElementWithFallback(
                COOKIE_ACCEPT_1, COOKIE_ACCEPT_2, COOKIE_ACCEPT_3, COOKIE_ACCEPT_4
            );
            if (cookieButton != null) {
                clickWithJsFallback(cookieButton);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            // Cookie banner not present
        }
    }

    public void closeModalIfPresent() {
        try {
            WebElement closeButton = findElementWithFallback(MODAL_CLOSE_1, MODAL_CLOSE_2);
            if (closeButton != null) {
                clickWithJsFallback(closeButton);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            // Modal not present
        }
    }

    public WebElement getFindAStoreLink() {
        return findElementWithFallback(
            HEADER_FIND_A_STORE_1, HEADER_FIND_A_STORE_2, HEADER_FIND_A_STORE_3
        );
    }

    public void clickFindAStore() {
        WebElement findStoreLink = getFindAStoreLink();
        if (findStoreLink != null) {
            clickWithJsFallback(findStoreLink);
        }
    }

    private WebElement findElementWithFallback(By... locators) {
        for (By locator : locators) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element != null && element.isDisplayed()) {
                    return element;
                }
            } catch (Exception e) {
                // Try next locator
            }
        }
        return null;
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }
}