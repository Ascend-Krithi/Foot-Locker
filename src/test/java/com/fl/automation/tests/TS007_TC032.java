package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS007_TC032 extends BaseTest {

    @Test(description = "TC4147: SCRUM-19509 TS-007 TC-001 - User dashboard verification")
    public void testUserDashboardVerification() {
        driver.get("https://marketplace.example.com/dashboard");
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Should navigate to user dashboard");
    }
}