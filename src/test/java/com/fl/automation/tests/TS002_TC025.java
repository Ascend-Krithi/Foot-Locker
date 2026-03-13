package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC025 extends BaseTest {

    @Test(description = "TC4140: SCRUM-19509 TS-002 TC-001 - Marketplace search by keyword")
    public void testMarketplaceSearchByKeyword() {
        driver.get("https://marketplace.example.com/search?q=solar");
        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Should navigate to search results page");
    }
}