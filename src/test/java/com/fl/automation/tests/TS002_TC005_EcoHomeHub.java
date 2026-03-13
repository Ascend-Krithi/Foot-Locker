package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.LoginPage;
import com.fl.automation.pages.InstallerPortalPage;

public class TS002_TC005_EcoHomeHub extends BaseTest {
    
    @Test
    public void testInstallerPortal() {
        driver.get("https://ecohomehub.example.com/installer/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("installer@example.com", "InstallerPass123");
        
        InstallerPortalPage portal = new InstallerPortalPage(driver);
        Assert.assertTrue(portal.isPortalDisplayed(), "Installer portal should be displayed");
        Assert.assertTrue(portal.areInstallerFeaturesVisible(), "Installer features should be visible");
    }
}