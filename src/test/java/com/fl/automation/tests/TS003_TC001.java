package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC001 extends BaseTest {

    @Test(description = "TC4111: SCRUM-19509 TS-003 TC-001 - Installer portal login")
    public void testInstallerPortalLogin() {
        driver.get("https://ecohomehub.example.com/installer/login");
        Assert.assertTrue(driver.getCurrentUrl().contains("installer") && driver.getCurrentUrl().contains("login"), 
            "Should navigate to installer login page");
    }
}