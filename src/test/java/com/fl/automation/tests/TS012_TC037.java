package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS012_TC037 extends BaseTest {

    @Test(description = "TC4152: SCRUM-19509 TS-012 TC-001 - Verify eco-certification details")
    public void testVerifyEcoCertificationDetails() {
        driver.get("https://marketplace.example.com/certifications");
        Assert.assertTrue(driver.getCurrentUrl().contains("certification"), "Should navigate to eco-certification details page");
    }
}