package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By nameInput = By.id("name");
    private By emailInput = By.id("email");
    private By phoneInput = By.id("phone");
    private By saveButton = By.cssSelector("button[type='submit']");
    private By successMessage = By.cssSelector(".success-message");
    
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public void updateProfile(String name, String phone) {
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(nameInput));
        nameField.clear();
        nameField.sendKeys(name);
        
        WebElement phoneField = driver.findElement(phoneInput);
        phoneField.clear();
        phoneField.sendKeys(phone);
    }
    
    public void saveProfile() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        clickWithJsFallback(button);
    }
    
    public boolean isUpdateSuccessful() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    private void clickWithJsFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}