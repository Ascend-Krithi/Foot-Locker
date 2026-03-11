package com.fl.automation.core;

import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.Arrays;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected StoreLocatorHelper storeLocatorHelper;

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
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

        String chromeBinary = System.getProperty("chromeBinary", System.getenv("CHROME_PATH"));
        if (chromeBinary != null && !chromeBinary.isBlank()) {
            options.setBinary(chromeBinary);
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        String baseUrl = System.getProperty("baseUrl", "https://www.footlocker.com/");
        driver.get(baseUrl);

        homePage = new HomePage(driver);
        storeLocatorHelper = new StoreLocatorHelper(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}