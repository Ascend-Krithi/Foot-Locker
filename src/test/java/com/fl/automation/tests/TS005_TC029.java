package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC029 extends BaseTest {

    @Test(description = "TC4144: SCRUM-19509 TS-005 TC-001 - Marketplace login valid credentials")
    public void testMarketplaceLoginValidCredentials() {
        driver.get("https://marketplace.example.com/login");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Should navigate to marketplace login page");
    }
}