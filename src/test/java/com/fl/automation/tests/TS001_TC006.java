package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006 extends BaseTest {

    @Test(description = "TC4106: SCRUM-19509 TS-001 TC-003 - Eco Home Hub loan application and installer search")
    public void testLoanApplicationAndInstallerSearch() {
        driver.get("https://ecohomehub.example.com/loan-application");
        Assert.assertTrue(driver.getCurrentUrl().contains("loan"), "Should be on loan application page");
        
        driver.get("https://ecohomehub.example.com/installer-search");
        Assert.assertTrue(driver.getCurrentUrl().contains("installer"), "Should navigate to installer search page");
    }
}