package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC001_VerifyStorePersistence extends BaseTest {

    @Test(description = "TC_4175: SCRUM-17166 TS-006 TC-001 - Verify store persistence")
    public void verifyStorePersistence() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToHomePage(baseUrl);
        Assert.assertTrue(homePage.isFindAStoreDisplayed(), "Find a Store should persist across sessions");
        
        driver.navigate().refresh();
        Assert.assertTrue(homePage.isFindAStoreDisplayed(), "Find a Store should still be displayed after refresh");
    }
}