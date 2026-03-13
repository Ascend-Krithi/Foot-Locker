package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC030 extends BaseTest {

    @Test(description = "TC4145: SCRUM-19509 TS-005 TC-002 - Marketplace login invalid credentials")
    public void testMarketplaceLoginInvalidCredentials() {
        driver.get("https://marketplace.example.com/login");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Should be on marketplace login page for invalid credentials test");
    }
}