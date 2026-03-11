package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import lombok.extern.slf4j.Slf4j;

/**
 * DriverFactory - Manages WebDriver instance creation and configuration
 * Uses Selenium Manager (no WebDriverManager.setup() needed)
 */
@Slf4j
public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Creates and configures ChromeDriver with headless options for CI/CD
     * @return WebDriver instance
     */
    public static WebDriver createDriver() {
        log.info("Creating ChromeDriver instance with headless configuration");
        
        ChromeOptions options = new ChromeOptions();
        
        // CI/CD optimized flags
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        
        // User agent to avoid detection
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        
        // Performance optimizations
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        
        WebDriver webDriver = new ChromeDriver(options);
        driver.set(webDriver);
        
        log.info("ChromeDriver created successfully");
        return webDriver;
    }

    /**
     * Gets the current thread's WebDriver instance
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quits the driver and removes it from ThreadLocal
     */
    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            log.info("Quitting WebDriver instance");
            webDriver.quit();
            driver.remove();
        }
    }
}