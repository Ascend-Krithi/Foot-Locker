package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC001_TestCase_SCRUM_15408_TS_002_TC_001 extends BaseTest {
    @Test(description = "Test Case - SCRUM-15408 TS-002 TC-001")
    public void testFindAStorePopupAndSelectMyStore() {
        test = extent.createTest("Test Case - SCRUM-15408 TS-002 TC-001");
        WebDriver driver = this.driver;

        // Step 1: Launch the Foot Locker homepage in a browser
        test.info("Step 1: Launch the Foot Locker homepage in a browser");
        HomePage homePage = new HomePage(driver);
        String url = ConfigReader.get("base.url");
        homePage.open(url);
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("foot locker"), "Foot Locker homepage loads successfully.");
        test.log(Status.PASS, "Foot Locker homepage loads successfully.");

        // Step 2: Click on the 'Find a Store' link/button
        test.info("Step 2: Click on the 'Find a Store' link/button");
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertNotNull(locatorPage.getSelectMyStoreLink(), "'Find a Store' popup appears.");
        test.log(Status.PASS, "'Find a Store' popup appears.");

        // Step 3: Click on the 'Select My Store' link in the popup
        test.info("Step 3: Click on the 'Select My Store' link in the popup");
        locatorPage.clickSelectMyStore();
        WebElement searchInput = locatorPage.getSearchInput();
        Assert.assertNotNull(searchInput, "'Find a Store' popup window opens with a 'Location' textbox and a 'Search for Stores' button.");
        test.log(Status.PASS, "'Find a Store' popup window opens with a 'Location' textbox and a 'Search for Stores' button.");
    }
}
