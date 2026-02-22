package com.example.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://yourapp.com/login");
    }

    @Test
    public void testValidLogin() {
        // Add Selenium code for login
        Assert.assertTrue(driver.getTitle().contains("Dashboard"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
