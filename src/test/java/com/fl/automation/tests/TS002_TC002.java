package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC002 extends BaseTest {

    @Test(description = "TC4109: SCRUM-19509 TS-002 TC-002 - Admin dashboard certification vetting")
    public void testAdminCertificationVetting() {
        driver.get("https://ecohomehub.example.com/admin/certifications");
        Assert.assertTrue(driver.getCurrentUrl().contains("admin") && driver.getCurrentUrl().contains("cert"), 
            "Should navigate to certification vetting page");
    }
}