package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS002_TC001_TestCase_SCRUM15408_TS002_TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver(ConfigReader.get("browser"));
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }

    @Test
    public void testHomePageTitle() {
        String title = DriverFactory.getDriver().getTitle();
        Assert.assertTrue(title.contains("Home"), "Title does not contain 'Home'");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
