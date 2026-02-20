package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_TestCase_SCRUM_15408_TS_001_TC_001 extends BaseTest {
    @Test(description = "Test Case - SCRUM-15408 TS-001 TC-001")
    public void testFindAStorePopupAppears() {
        test = extent.createTest("Test Case - SCRUM-15408 TS-001 TC-001");
        WebDriver driver = this.driver;

        // Step 2: Launch the Foot Locker homepage in a browser
        test.info("Step 2: Launch the Foot Locker homepage in a browser");
        HomePage homePage = new HomePage(driver);
        String url = ConfigReader.get("base.url");
        homePage.open(url);
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("foot locker"), "Foot Locker homepage loads successfully.");
        test.log(Status.PASS, "Foot Locker homepage loads successfully.");

        // Step 3: Click on the 'Find a Store' link/button
        test.info("Step 3: Click on the 'Find a Store' link/button");
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertNotNull(locatorPage.getSelectMyStoreLink(), "'Find a Store' popup appears below the link/button.");
        test.log(Status.PASS, "'Find a Store' popup appears below the link/button.");

        // Step 4: Observe the popup content
        test.info("Step 4: Observe the popup content");
        String popupText = locatorPage.getSelectMyStoreLink().getText();
        Assert.assertTrue(popupText.contains("Select My Store") || popupText.contains("Set My Store") || popupText.contains("Make This My Store") || popupText.contains("Set as My Store"), "Popup displays the message and a visible 'Select My Store' link.");
        test.log(Status.PASS, "Popup displays the message and a visible 'Select My Store' link.");
    }
}
