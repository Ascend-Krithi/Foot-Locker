package com.example.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogoutTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://yourapp.com/dashboard");
    }

    @Test
    public void testLogout() {
        // Add Selenium code for logout
        Assert.assertTrue(driver.getTitle().contains("Login"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
