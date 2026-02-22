package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS001_TC001_TestCaseSCRUM15408TS001TC001 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void verifyStoreLocatorPopupMessage() {
        HomePage homePage = new HomePage();
        homePage.clickStoreLocator();
        StoreLocatorPage storeLocatorPage = new StoreLocatorPage();
        String popupMsg = storeLocatorPage.getPopupMessage();
        Assert.assertTrue(popupMsg.contains("Choose a preferred store to make shopping easier"),
                "Popup message did not contain expected text. Actual: " + popupMsg);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
