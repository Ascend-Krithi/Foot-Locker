package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS015_TC041 extends BaseTest {

    @Test(description = "TC4156: SCRUM-19509 TS-015 TC-001 - Access help and support")
    public void testAccessHelpAndSupport() {
        driver.get("https://marketplace.example.com/help");
        Assert.assertTrue(driver.getCurrentUrl().contains("help") || driver.getCurrentUrl().contains("support"), 
            "Should navigate to help and support page");
    }
}