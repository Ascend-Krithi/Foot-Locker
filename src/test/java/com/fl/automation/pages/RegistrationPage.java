package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By nameInput = By.id("name");
    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By confirmPasswordInput = By.id("confirmPassword");
    private By phoneInput = By.id("phone");
    private By addressInput = By.id("address");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By successMessage = By.cssSelector(".success-message, .alert-success");
    
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public void fillRegistrationForm(String name, String email, String password, String phone, String address) {
        wait.until(ExpectedConditions.presenceOfElementLocated(nameInput)).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(confirmPasswordInput).sendKeys(password);
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(addressInput).sendKeys(address);
    }
    
    public void submitForm() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        clickWithJsFallback(button);
    }
    
    public boolean isRegistrationSuccessful() {
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