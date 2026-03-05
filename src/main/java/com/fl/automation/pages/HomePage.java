
package com.fl.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver){

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void acceptCookiesIfPresent(){

        try{
            WebElement cookie = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            cookie.click();
        }catch(Exception ignored){}
    }

    public void closeFlxRewardsIfPresent(){

        try{
            WebElement close = driver.findElement(By.xpath("//button[contains(@aria-label,'close')]"));
            close.click();
        }catch(Exception ignored){}
    }

    public void clickFindAStore(){

        WebElement store = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(text(),'Find a Store')]")));

        store.click();
    }
}
