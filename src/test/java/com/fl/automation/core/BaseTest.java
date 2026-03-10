package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.Arrays;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();

        boolean headless = Boolean.parseBoolean(
            System.getProperty("headless",
                System.getenv().getOrDefault("HEADLESS", "true"))
        );
        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--lang=en-US");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        String chromeBinary = System.getProperty("chromeBinary", System.getenv("CHROME_PATH"));
        if (chromeBinary != null && !chromeBinary.isBlank()) {
            options.setBinary(chromeBinary);
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        String baseUrl = System.getProperty("baseUrl", "https://www.footlocker.com/");
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
