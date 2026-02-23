package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private final WebDriver driver;
    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }
    public List<WebElement> getStoreCards() {
        return driver.findElements(By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']"));
    }
    public String getStoreAddress(WebElement card) {
        WebElement address = card.findElement(By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']"));
        return BrowserUtils.getText(driver, address);
    }
    public void setMyStore(WebElement card) {
        WebElement btn = card.findElement(By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]") );
        BrowserUtils.click(driver, btn);
    }
    public boolean isSelectMyStoreLinkPresent() {
        try {
            driver.findElement(By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]") );
            return true;
        } catch (Exception e) {
            try {
                driver.findElement(By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]") );
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }
}