package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: TC 2432
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Launch Foot Locker homepage, Click 'Find a Store' link in header, Verify popup displays message 'Choose a preferred store to make shopping easier', Verify 'Select My Store' link is visible
 */
public class TS001_TC001_ {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage();
    }

    @Test
    public void testFindAStorePopup() {
        homePage.clickFindAStore();
        boolean popupMsg = driver.getPageSource().contains("Choose a preferred store to make shopping easier");
        Assert.assertTrue(popupMsg, "Popup message not displayed");
        boolean selectMyStoreVisible = driver.findElements(By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")).size() > 0;
        Assert.assertTrue(selectMyStoreVisible, "'Select My Store' link not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
