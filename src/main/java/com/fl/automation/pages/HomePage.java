package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    private static final String BASE_URL = "https://www.footlocker.com";
    private static final String SNEAKERS_URL = "https://www.footlocker.com/sneakers";
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public void navigateToHomePage() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.urlContains("footlocker.com"));
    }
    
    public void navigateToSneakersPage() {
        driver.get(SNEAKERS_URL);
        wait.until(ExpectedConditions.urlContains("sneakers"));
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}