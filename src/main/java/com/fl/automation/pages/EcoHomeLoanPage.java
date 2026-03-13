package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class EcoHomeLoanPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> LOAN_AMOUNT_INPUT = Arrays.asList(
            By.cssSelector("input[name='loanAmount']"),
            By.cssSelector("input[id='loanAmount']"),
            By.xpath("//input[contains(@placeholder,'Loan Amount')]")
    );

    private static final List<By> INCOME_INPUT = Arrays.asList(
            By.cssSelector("input[name='income']"),
            By.cssSelector("input[id='income']"),
            By.xpath("//input[contains(@placeholder,'Income')]")
    );

    private static final List<By> APPLY_BUTTON = Arrays.asList(
            By.cssSelector("button[type='submit']"),
            By.xpath("//button[contains(.,'Apply')]"),
            By.xpath("//button[contains(.,'Submit Application')]")
    );

    private static final List<By> SUCCESS_MESSAGE = Arrays.asList(
            By.cssSelector("div[class*='success']"),
            By.xpath("//div[contains(.,'Application submitted')]"),
            By.xpath("//div[contains(@class,'alert-success')]")
    );

    private static final List<By> ERROR_MESSAGE = Arrays.asList(
            By.cssSelector("div[class*='error']"),
            By.xpath("//div[contains(@class,'alert-error')]"),
            By.xpath("//span[contains(@class,'error')]")
    );

    public EcoHomeLoanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void applyForLoan(String loanAmount, String income) {
        enterLoanAmount(loanAmount);
        enterIncome(income);
        clickApply();
    }

    public void enterLoanAmount(String amount) {
        WebElement input = findElementWithFallback(LOAN_AMOUNT_INPUT);
        input.clear();
        input.sendKeys(amount);
    }

    public void enterIncome(String income) {
        WebElement input = findElementWithFallback(INCOME_INPUT);
        input.clear();
        input.sendKeys(income);
    }

    public void clickApply() {
        WebElement button = findElementWithFallback(APPLY_BUTTON);
        clickWithJsFallback(button);
    }

    public boolean isApplicationSuccessful() {
        try {
            WebElement message = findElementWithFallback(SUCCESS_MESSAGE);
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        try {
            WebElement error = findElementWithFallback(ERROR_MESSAGE);
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        WebElement error = findElementWithFallback(ERROR_MESSAGE);
        return error.getText();
    }

    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}