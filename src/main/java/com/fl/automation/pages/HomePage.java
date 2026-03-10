package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By[] findStoreLocators = new By[]{
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private By[] selectMyStoreLocators = new By[]{
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private By[] searchInputLocators = new By[]{
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private By searchButtonLocator = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void clickFindStore(){
        WebElement elem = findElementWithFallback(findStoreLocators);
        clickWithFallback(elem);
    }

    public void clickSelectMyStore(){
        WebElement elem = findElementWithFallback(selectMyStoreLocators);
        clickWithFallback(elem);
    }

    public void enterLocation(String location){
        WebElement input = findElementWithFallback(searchInputLocators);
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchButton(){
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
        clickWithFallback(btn);
    }

    public boolean isSelectMyStoreLinkVisible(){
        try{
            WebElement elem = findElementWithFallback(selectMyStoreLocators);
            return elem.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isLocationTextboxVisible(){
        try{
            WebElement elem = findElementWithFallback(searchInputLocators);
            return elem.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isSearchButtonVisible(){
        try{
            WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButtonLocator));
            return btn.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    private WebElement findElementWithFallback(By[] locators){
        for(By by : locators){
            try{
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }catch(TimeoutException ignored){}
        }
        throw new NoSuchElementException("None of the fallback locators found an element");
    }

    private void clickWithFallback(WebElement elem){
        try{
            elem.click();
        }catch(Exception e){
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", elem);
        }
    }
}
