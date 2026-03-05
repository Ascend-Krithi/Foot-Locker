package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;

    private By[] selectMyStoreLocators = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]") ,
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private By[] searchInputLocators = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSelectMyStoreLink() {
        for (By locator : selectMyStoreLocators) {
            try {
                WebElement el = driver.findElement(locator);
                if (el.isDisplayed()) return el;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Select My Store link/button not found");
    }

    public void clickSelectMyStore() {
        BrowserUtils.click(driver, getSelectMyStoreLink());
    }

    public WebElement getSearchInput() {
        for (By locator : searchInputLocators) {
            try {
                WebElement el = driver.findElement(locator);
                if (el.isDisplayed()) return el;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Search input not found");
    }

    public void enterLocation(String location) {
        BrowserUtils.type(driver, getSearchInput(), location);
    }

    public void clickSearchButton() {
        WebElement searchBtn = getSearchInput().findElement(By.xpath("../following-sibling::button[contains(.,'Search') or @type='submit']"));
        BrowserUtils.click(driver, searchBtn);
    }
}