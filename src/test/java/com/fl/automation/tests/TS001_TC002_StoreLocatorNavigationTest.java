package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS001_TC002_StoreLocatorNavigationTest {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testStoreLocatorNavigation() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickStoreLocator();
        Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("store-locator"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
