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

public class EcoHomeInstallerPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> SEARCH_INPUT = Arrays.asList(
            By.cssSelector("input[name='search']"),
            By.cssSelector("input[placeholder*='Search']"),
            By.xpath("//input[contains(@placeholder,'installer')]")
    );

    private static final List<By> SEARCH_BUTTON = Arrays.asList(
            By.cssSelector("button[type='submit']"),
            By.xpath("//button[contains(.,'Search')]"),
            By.cssSelector("button[class*='search']")
    );

    private static final List<By> INSTALLER_RESULTS = Arrays.asList(
            By.cssSelector("div[class*='installer-card']"),
            By.cssSelector("div[data-qa='installer']"),
            By.xpath("//div[contains(@class,'installer')]")
    );

    private static final List<By> SEND_LEAD_BUTTON = Arrays.asList(
            By.xpath("//button[contains(.,'Send Lead')]"),
            By.xpath("//button[contains(.,'Contact')]"),
            By.cssSelector("button[class*='send-lead']")
    );

    public EcoHomeInstallerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void searchForInstaller(String location) {
        WebElement input = findElementWithFallback(SEARCH_INPUT);
        input.clear();
        input.sendKeys(location);
        WebElement button = findElementWithFallback(SEARCH_BUTTON);
        clickWithJsFallback(button);
    }

    public boolean areInstallersDisplayed() {
        try {
            List<WebElement> results = driver.findElements(INSTALLER_RESULTS.get(0));
            return results != null && results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendProjectLead() {
        WebElement button = findElementWithFallback(SEND_LEAD_BUTTON);
        clickWithJsFallback(button);
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