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

    private By findStoreLink1 = By.linkText("Find a Store");
    private By findStoreLink2 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreLink3 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");
    private By findStoreLink4 = By.xpath("//*[normalize-space()='Find a Store' or normalize-space()='Find a store']");
    private By findStoreLink5 = By.cssSelector("a[href*='store-locator'], a[aria-label*='Find a Store' i], button[aria-label*='Find a Store' i]");
    private By findStoreLink6 = By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'find a store')]");

    private By storePopup = By.cssSelector("div[class*='store-popup'], div[class*='store-selector'], div[class*='store-locator-popup']");
    
    private By selectMyStoreLink1 = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreLink2 = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    private By cookieAccept1 = By.id("onetrust-accept-btn-handler");
    private By cookieAccept2 = By.cssSelector("button#onetrust-accept-btn-handler");
    private By cookieAccept3 = By.cssSelector("button[aria-label*='Accept' i]");
    private By cookieAccept4 = By.xpath("//button[contains(normalize-space(.),'Accept All Cookies') or contains(normalize-space(.),'Accept Cookies') or contains(normalize-space(.),'I Accept') or contains(normalize-space(.),'Accept All')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void dismissCookieConsent() {
        try {
            WebElement cookieBtn = null;
            try {
                cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAccept1));
            } catch (Exception e1) {
                try {
                    cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAccept2));
                } catch (Exception e2) {
                    try {
                        cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAccept3));
                    } catch (Exception e3) {
                        cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAccept4));
                    }
                }
            }
            if (cookieBtn != null) {
                cookieBtn.click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }

    public void clickFindStore() {
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink1));
            element.click();
            return;
        } catch (Exception e1) {
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink2));
                element.click();
                return;
            } catch (Exception e2) {
                try {
                    element = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink3));
                    element.click();
                    return;
                } catch (Exception e3) {
                    try {
                        element = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink4));
                        element.click();
                        return;
                    } catch (Exception e4) {
                        try {
                            element = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink5));
                            element.click();
                            return;
                        } catch (Exception e5) {
                            try {
                                element = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink6));
                                element.click();
                                return;
                            } catch (Exception e6) {
                                element = driver.findElement(findStoreLink1);
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isStorePopupDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storePopup)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement element = null;
            try {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreLink1));
            } catch (Exception e1) {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreLink2));
            }
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = null;
        try {
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink1));
                element.click();
                return;
            } catch (Exception e1) {
                element = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink2));
                element.click();
                return;
            }
        } catch (Exception e) {
            try {
                element = driver.findElement(selectMyStoreLink1);
            } catch (Exception ex) {
                element = driver.findElement(selectMyStoreLink2);
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}