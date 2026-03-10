package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public List<WebElement> getStoreCards(){

        By locator = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");

        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public String getStoreAddress(WebElement card){

        By addressLocator = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");

        return card.findElement(addressLocator).getText();
    }

    public void clickSetMyStore(WebElement card){

        By buttonLocator = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");

        WebElement btn = card.findElement(buttonLocator);

        try{
            btn.click();
        }catch(Exception e){
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", btn);
        }
    }
}
