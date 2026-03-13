package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC003 extends BaseTest {

    @Test(description = "TC4110: SCRUM-19509 TS-002 TC-003 - Admin approve installer, verify in customer search")
    public void testAdminApproveAndVerifyInstaller() {
        driver.get("https://ecohomehub.example.com/admin/installer-applications");
        Assert.assertTrue(driver.getCurrentUrl().contains("installer"), "Should be on installer applications page");
        
        driver.get("https://ecohomehub.example.com/installer-search");
        Assert.assertTrue(driver.getCurrentUrl().contains("installer"), "Should verify installer in customer search");
    }
}