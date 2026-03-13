package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> COOKIE_ACCEPT_LOCATORS = Arrays.asList(
        By.id("onetrust-accept-btn-handler"),
        By.cssSelector("button#onetrust-accept-btn-handler"),
        By.cssSelector("button[aria-label*='Accept' i]"),
        By.xpath("//button[contains(normalize-space(.),'Accept All Cookies')]")
    );

    private static final List<By> HEADER_FIND_STORE_LOCATORS = Arrays.asList(
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store')]"),
        By.xpath("//*[normalize-space()='Find a Store']")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void open(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void acceptCookies() {
        WebElement cookieButton = findElementWithFallback(COOKIE_ACCEPT_LOCATORS);
        if (cookieButton != null) {
            clickWithJsFallback(cookieButton);
        }
    }

    public void clickFindStore() {
        WebElement findStoreLink = findElementWithFallback(HEADER_FIND_STORE_LOCATORS);
        clickWithJsFallback(findStoreLink);
    }

    public boolean isFindStoreDisplayed() {
        try {
            WebElement element = findElementWithFallback(HEADER_FIND_STORE_LOCATORS);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}