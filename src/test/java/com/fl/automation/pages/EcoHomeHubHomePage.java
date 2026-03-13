package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EcoHomeHubHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By registerLink = By.linkText("Register");
    private By loginLink = By.linkText("Login");
    private By aboutLink = By.linkText("About");
    private By contactLink = By.linkText("Contact");
    
    public EcoHomeHubHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public void clickRegister() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        clickWithJsFallback(element);
    }
    
    public void clickLogin() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        clickWithJsFallback(element);
    }
    
    public boolean isHomePageDisplayed() {
        return driver.getTitle().contains("Eco Home Hub") || driver.getCurrentUrl().contains("ecohomehub");
    }
    
    private void clickWithJsFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}