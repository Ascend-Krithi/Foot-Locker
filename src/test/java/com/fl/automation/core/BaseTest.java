package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // IMPORTANT:
        // Do NOT call WebDriverManager.chromedriver().setup() here.
        // Selenium 4.21+ uses Selenium Manager to match the correct driver to the installed Chrome.

        ChromeOptions options = new ChromeOptions();

        // Headless ON by default in CI (can be OFF locally)
        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless",
                        System.getenv().getOrDefault("HEADLESS", "true"))
        );
        if (headless) {
            options.addArguments("--headless=new");
        }

        // MUST-HAVE flags for Linux CI stability (GitHub Actions)
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        // If workflow provides Chrome binary path, honor it
        String chromeBinary = System.getProperty("chromeBinary",
                                System.getenv("CHROME_PATH"));
        if (chromeBinary != null && !chromeBinary.isBlank()) {
            options.setBinary(chromeBinary);
        }

        // Do NOT call maximize() in headless; rely on --window-size
        driver = new ChromeDriver(options);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
