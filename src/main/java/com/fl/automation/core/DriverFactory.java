package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Use existing getDriver() from DriverFactory
        driver = DriverFactory.getDriver();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        driver.get("https://www.footlocker.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if(driver != null){
            DriverFactory.quitDriver();  // Use quitDriver method
        }
    }
}
