package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS007_TC001_TestCase_SCRUM15408_TS007_TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver(ConfigReader.get("browser"));
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }

    @Test
    public void testZipCodeInputMaxLength() {
        HomePage homePage = new HomePage();
        homePage.clickStoreLocator();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.enterZipCode("1234567890");
        String value = DriverFactory.getDriver().findElement(org.openqa.selenium.By.id("zip-code-input")).getAttribute("value");
        Assert.assertTrue(value.length() <= 10, "Zip code input exceeds max length");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
