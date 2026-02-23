package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS006_TC001_TestCase_SCRUM15408_TS006_TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver(ConfigReader.get("browser"));
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }

    @Test
    public void testSearchButtonEnabled() {
        HomePage homePage = new HomePage();
        homePage.clickStoreLocator();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        boolean enabled = DriverFactory.getDriver().findElement(org.openqa.selenium.By.id("search-button")).isEnabled();
        Assert.assertTrue(enabled, "Search button is not enabled");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
