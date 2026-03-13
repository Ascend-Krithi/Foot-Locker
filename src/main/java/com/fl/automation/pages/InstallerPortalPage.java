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

public class InstallerPortalPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> USERNAME_INPUT = Arrays.asList(
            By.cssSelector("input[name='username']"),
            By.cssSelector("input[id='username']"),
            By.xpath("//input[contains(@placeholder,'Username')]")
    );

    private static final List<By> PASSWORD_INPUT = Arrays.asList(
            By.cssSelector("input[name='password']"),
            By.cssSelector("input[type='password']"),
            By.xpath("//input[contains(@placeholder,'Password')]")
    );

    private static final List<By> LOGIN_BUTTON = Arrays.asList(
            By.cssSelector("button[type='submit']"),
            By.xpath("//button[contains(.,'Login')]"),
            By.xpath("//button[contains(.,'Sign In')]")
    );

    private static final List<By> PROJECT_LEADS = Arrays.asList(
            By.cssSelector("div[class*='project-lead']"),
            By.xpath("//div[contains(@class,'lead')]"),
            By.cssSelector("table tbody tr")
    );

    public InstallerPortalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void login(String username, String password) {
        WebElement usernameInput = findElementWithFallback(USERNAME_INPUT);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        
        WebElement passwordInput = findElementWithFallback(PASSWORD_INPUT);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        
        WebElement loginButton = findElementWithFallback(LOGIN_BUTTON);
        clickWithJsFallback(loginButton);
    }

    public boolean areProjectLeadsDisplayed() {
        try {
            List<WebElement> leads = driver.findElements(PROJECT_LEADS.get(0));
            return leads != null && leads.size() > 0;
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