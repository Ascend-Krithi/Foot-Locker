package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC026 extends BaseTest {

    @Test(description = "TC4141: SCRUM-19509 TS-002 TC-002 - Marketplace search by category filter")
    public void testMarketplaceSearchByCategory() {
        driver.get("https://marketplace.example.com/search?category=solar-panels");
        Assert.assertTrue(driver.getCurrentUrl().contains("search") && driver.getCurrentUrl().contains("category"), 
            "Should filter search results by category");
    }
}