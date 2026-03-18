package com.fl.automation.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");   // ✅ explicit size for Linux/Xvfb
        options.addArguments("--window-position=0,0");     // ✅ start at top-left corner
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--force-device-scale-factor=1"); // ✅ prevent scaling issues

        WebDriver driver = new ChromeDriver(options);

        // ✅ Force exact 1920x1080 after launch — overrides any OS-level scaling
        driver.manage().window().setSize(new Dimension(1920, 1080));

        return driver;
    }
}
