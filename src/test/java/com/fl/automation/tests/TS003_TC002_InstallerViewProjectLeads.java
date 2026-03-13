package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.InstallerPortalPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC002_InstallerViewProjectLeads extends BaseTest {

    @Test(description = "TC_4112: SCRUM-19509 TS-003 TC-002 - Installer view project leads")
    public void testInstallerViewProjectLeads() {
        InstallerPortalPage installerPortal = new InstallerPortalPage(driver);
        
        driver.get("https://eco-home-hub.example.com/installer");
        installerPortal.login("installer1", "installer123");
        
        boolean areLeadsDisplayed = installerPortal.areProjectLeadsDisplayed();
        Assert.assertTrue(areLeadsDisplayed, "Project leads should be displayed");
    }
}