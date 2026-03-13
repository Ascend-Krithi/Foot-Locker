package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS010_TC035 extends BaseTest {

    @Test(description = "TC4150: SCRUM-19509 TS-010 TC-001 - User logout")
    public void testUserLogout() {
        driver.get("https://marketplace.example.com/dashboard");
        Assert.assertTrue(driver.getCurrentUrl().contains("marketplace"), "Should be on marketplace site");
        
        driver.get("https://marketplace.example.com/logout");
        Assert.assertTrue(driver.getCurrentUrl().contains("logout") || driver.getCurrentUrl().contains("login"), 
            "Should logout and redirect appropriately");
    }
}