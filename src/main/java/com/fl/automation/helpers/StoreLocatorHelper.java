package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchInputCss1 = By.cssSelector("input[type='search']");
    private By searchInputCss2 = By.cssSelector("input[name='q']");
    private By searchInputCss3 = By.cssSelector("input[aria-label*='Search']");
    private By searchInputCss4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");

    private By selectMyStoreXpath1 = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreXpath2 = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButtonInCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public boolean isSearchInputVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputCss1));
            return true;
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputCss2));
                return true;
            } catch (Exception e2) {
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputCss3));
                    return true;
                } catch (Exception e3) {
                    try {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputCss4));
                        return true;
                    } catch (Exception e4) {
                        return false;
                    }
                }
            }
        }
    }

    public void enterLocation(String location) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputCss1));
            element.clear();
            element.sendKeys(location);
        } catch (Exception e) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputCss2));
                element.clear();
                element.sendKeys(location);
            } catch (Exception e2) {
                try {
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputCss3));
                    element.clear();
                    element.sendKeys(location);
                } catch (Exception e3) {
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputCss4));
                    element.clear();
                    element.sendKeys(location);
                }
            }
        }
    }

    public void clickSearchButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchInputCss1));
            element.submit();
        } catch (Exception e) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchInputCss2));
                element.submit();
            } catch (Exception e2) {
                try {
                    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchInputCss3));
                    element.submit();
                } catch (Exception e3) {
                    WebElement element = driver.findElement(searchInputCss1);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].form.submit();", element);
                }
            }
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultCards));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreXpath1));
            return true;
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreXpath2));
                return true;
            } catch (Exception e2) {
                return false;
            }
        }
    }

    public void clickSelectMyStore() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreXpath1));
            element.click();
        } catch (Exception e) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreXpath2));
                element.click();
            } catch (Exception e2) {
                WebElement element = driver.findElement(selectMyStoreXpath1);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        }
    }
}