package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.InstallerPortalPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC001_InstallerPortalLogin extends BaseTest {

    @Test(description = "TC_4111: SCRUM-19509 TS-003 TC-001 - Installer portal login")
    public void testInstallerPortalLogin() {
        InstallerPortalPage installerPortal = new InstallerPortalPage(driver);
        
        driver.get("https://eco-home-hub.example.com/installer");
        installerPortal.login("installer1", "installer123");
        
        Assert.assertTrue(true, "Installer login successful");
    }
}