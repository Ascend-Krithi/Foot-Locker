package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC024 extends BaseTest {

    @Test(description = "TC4139: SCRUM-19509 TS-001 TC-001 - Marketplace home page load and elements")
    public void testMarketplaceHomePageLoad() {
        driver.get("https://marketplace.example.com");
        Assert.assertTrue(driver.getCurrentUrl().contains("marketplace"), "Should navigate to marketplace home page");
        Assert.assertFalse(driver.getTitle().isEmpty(), "Page title should be present");
    }
}