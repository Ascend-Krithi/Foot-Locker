package com.fl.automation.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver() {
        String browser = ConfigReader.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(ConfigReader.getProperty("implicitWait")), TimeUnit.SECONDS);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}