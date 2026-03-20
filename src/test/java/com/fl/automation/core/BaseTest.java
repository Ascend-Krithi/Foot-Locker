package com.fl.automation.core;

import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    
    protected WebDriver driver;
    protected HomePage homePage;
    protected StoreLocatorHelper storeLocatorHelper;
    
    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.footlocker.com");
        
        homePage = new HomePage(driver);
        storeLocatorHelper = new StoreLocatorHelper(driver);
    }
    
    @AfterMethod
    public void teardown() {
        DriverFactory.quitDriver();
    }
}