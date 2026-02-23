package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By locationInput = By.cssSelector("input[type='search']");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickSelectMyStore() {
        if (driver.findElements(selectMyStoreLink).size() > 0) {
            BrowserUtils.click(driver, driver.findElement(selectMyStoreLink));
        } else if (driver.findElements(selectMyStoreButton).size() > 0) {
            BrowserUtils.click(driver, driver.findElement(selectMyStoreButton));
        }
    }

    public void enterLocation(String location) {
        WebElement input;
        if (driver.findElements(locationInput).size() > 0) {
            input = driver.findElement(locationInput);
        } else if (driver.findElements(By.cssSelector("input[name='q']")).size() > 0) {
            input = driver.findElement(By.cssSelector("input[name='q']"));
        } else if (driver.findElements(By.cssSelector("input[aria-label*='Search']")).size() > 0) {
            input = driver.findElement(By.cssSelector("input[aria-label*='Search']"));
        } else {
            input = driver.findElement(By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]"));
        }
        BrowserUtils.type(driver, input, location);
    }

    public void clickSearchForStores() {
        BrowserUtils.click(driver, driver.findElement(searchButton));
    }
}
