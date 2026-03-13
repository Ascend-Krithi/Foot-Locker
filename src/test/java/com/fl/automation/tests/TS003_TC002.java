package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC002 extends BaseTest {

    @Test(description = "TC4112: SCRUM-19509 TS-003 TC-002 - Installer portal view project leads")
    public void testInstallerViewProjectLeads() {
        driver.get("https://ecohomehub.example.com/installer/login");
        Assert.assertTrue(driver.getCurrentUrl().contains("installer"), "Should be on installer portal");
        
        driver.get("https://ecohomehub.example.com/installer/leads");
        Assert.assertTrue(driver.getCurrentUrl().contains("leads"), "Should navigate to project leads page");
    }
}