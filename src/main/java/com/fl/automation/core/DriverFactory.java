package com.fl.automation.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        if (driver.get() == null) {
            String browser = ConfigReader.get("browser");
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                driver.set(new ChromeDriver(options));
            } else {
                throw new RuntimeException("Unsupported browser: " + browser);
            }
            driver.get().manage().timeouts().implicitlyWait(Integer.parseInt(ConfigReader.get("timeout")), TimeUnit.SECONDS);
            driver.get().manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
