package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By findStoreButton = By.xpath(
        "//a[contains(@href,'store-locator')] | " +
        "//a[contains(normalize-space(),'Find a Store')] | " +
        "//span[contains(normalize-space(),'Find a Store')] | " +
        "//button[contains(normalize-space(),'Find a Store')]"
    );

    private By storeDropdown = By.xpath(
        "//*[contains(text(),'Choose a preferred store') or " +
        "contains(text(),'Select my store') or " +
        "contains(text(),'Find a Store')]"
    );

    private By selectMyStoreLink = By.xpath(
        "//a[contains(normalize-space(),'Select my store')] | " +
        "//button[contains(normalize-space(),'Select my store')] | " +
        "//*[contains(@class,'store') and contains(normalize-space(),'Select my store')]"
    );

    private By cookieAcceptButton = By.id("onetrust-accept-btn-handler");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void acceptCookiesIfPresent() {
