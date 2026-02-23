package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;
import java.util.List;

public class StoreLocatorPage {
    private WebDriver driver;
    private By[] selectMyStoreLocators = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')"]),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')"])
    };
    private By[] searchInputLocators = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");
    private By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopupMessageDisplayed() {
        try {
            return driver.findElement(popupMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        for (By locator : selectMyStoreLocators) {
            try {
                WebElement link = driver.findElement(locator);
                if (link.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public void clickSelectMyStore() {
        for (By locator : selectMyStoreLocators) {
            try {
                WebElement link = driver.findElement(locator);
                if (link.isDisplayed()) {
                    BrowserUtils.click(driver, link);
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Select My Store link/button not found");
    }

    public boolean isLocationTextboxPresent() {
        for (By locator : searchInputLocators) {
            try {
                WebElement input = driver.findElement(locator);
                if (input.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public void enterLocation(String location) {
        for (By locator : searchInputLocators) {
            try {
                WebElement input = driver.findElement(locator);
                if (input.isDisplayed()) {
                    BrowserUtils.type(driver, input, location);
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Location textbox not found");
    }

    public void clickSearchForStores() {
        WebElement btn = driver.findElement(searchButton);
        BrowserUtils.click(driver, btn);
    }
}
