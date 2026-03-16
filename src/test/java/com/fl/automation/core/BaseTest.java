package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    
    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.footlocker.com");
    }
    
    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}