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

public class EcoHomeRegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> FIRST_NAME_INPUT = Arrays.asList(
            By.cssSelector("input[name='firstName']"),
            By.cssSelector("input[id='firstName']"),
            By.xpath("//input[contains(@placeholder,'First Name')]")
    );

    private static final List<By> LAST_NAME_INPUT = Arrays.asList(
            By.cssSelector("input[name='lastName']"),
            By.cssSelector("input[id='lastName']"),
            By.xpath("//input[contains(@placeholder,'Last Name')]")
    );

    private static final List<By> EMAIL_INPUT = Arrays.asList(
            By.cssSelector("input[name='email']"),
            By.cssSelector("input[type='email']"),
            By.xpath("//input[contains(@placeholder,'Email')]")
    );

    private static final List<By> PASSWORD_INPUT = Arrays.asList(
            By.cssSelector("input[name='password']"),
            By.cssSelector("input[type='password']"),
            By.xpath("//input[contains(@placeholder,'Password')]")
    );

    private static final List<By> REGISTER_BUTTON = Arrays.asList(
            By.cssSelector("button[type='submit']"),
            By.xpath("//button[contains(.,'Register')]"),
            By.xpath("//button[contains(.,'Sign Up')]")
    );

    private static final List<By> SUCCESS_MESSAGE = Arrays.asList(
            By.cssSelector("div[class*='success']"),
            By.xpath("//div[contains(.,'Registration successful')]"),
            By.xpath("//div[contains(@class,'alert-success')]")
    );

    public EcoHomeRegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateToEcoHomeHub() {
        driver.get("https://eco-home-hub.example.com");
    }

    public void registerCustomer(String firstName, String lastName, String email, String password) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        clickRegister();
    }

    public void enterFirstName(String firstName) {
        WebElement input = findElementWithFallback(FIRST_NAME_INPUT);
        input.clear();
        input.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement input = findElementWithFallback(LAST_NAME_INPUT);
        input.clear();
        input.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement input = findElementWithFallback(EMAIL_INPUT);
        input.clear();
        input.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement input = findElementWithFallback(PASSWORD_INPUT);
        input.clear();
        input.sendKeys(password);
    }

    public void clickRegister() {
        WebElement button = findElementWithFallback(REGISTER_BUTTON);
        clickWithJsFallback(button);
    }

    public boolean isRegistrationSuccessful() {
        try {
            WebElement message = findElementWithFallback(SUCCESS_MESSAGE);
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
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