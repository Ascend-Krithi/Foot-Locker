package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoanApplicationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By loanAmountInput = By.id("loanAmount");
    private By purposeInput = By.id("purpose");
    private By incomeInput = By.id("income");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By confirmationMessage = By.cssSelector(".confirmation-message");
    
    public LoanApplicationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public void fillLoanApplication(String amount, String purpose, String income) {
        wait.until(ExpectedConditions.presenceOfElementLocated(loanAmountInput)).sendKeys(amount);
        driver.findElement(purposeInput).sendKeys(purpose);
        driver.findElement(incomeInput).sendKeys(income);
    }
    
    public void submitApplication() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        clickWithJsFallback(button);
    }
    
    public boolean isApplicationSubmitted() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(confirmationMessage)).isDisplayed();
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