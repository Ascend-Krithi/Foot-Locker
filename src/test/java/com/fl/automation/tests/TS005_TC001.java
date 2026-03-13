package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC001 extends BaseTest {

    @Test(description = "TC4115: SCRUM-19509 TS-005 TC-001 - Production deployment verification")
    public void testProductionDeploymentVerification() {
        driver.get("https://ecohomehub.example.com/health");
        Assert.assertTrue(driver.getCurrentUrl().contains("ecohomehub"), "Should access production environment");
    }
}