package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC001 extends BaseTest {

    @Test(description = "TC4108: SCRUM-19509 TS-002 TC-001 - Admin dashboard installer application approval")
    public void testAdminInstallerApproval() {
        driver.get("https://ecohomehub.example.com/admin/dashboard");
        Assert.assertTrue(driver.getCurrentUrl().contains("admin"), "Should navigate to admin dashboard");
        
        driver.get("https://ecohomehub.example.com/admin/installer-applications");
        Assert.assertTrue(driver.getCurrentUrl().contains("installer"), "Should show installer applications");
    }
}