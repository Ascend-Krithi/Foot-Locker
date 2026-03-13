package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.AdminDashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC001_AdminLogin extends BaseTest {

    @Test(description = "TC_4108: SCRUM-19509 TS-002 TC-001 - Admin login, approve installer application")
    public void testAdminLogin() {
        AdminDashboardPage adminPage = new AdminDashboardPage(driver);
        
        driver.get("https://eco-home-hub.example.com/admin");
        adminPage.login("admin", "admin123");
        
        boolean areApplicationsDisplayed = adminPage.areInstallerApplicationsDisplayed();
        Assert.assertTrue(areApplicationsDisplayed, "Installer applications should be displayed");
    }
}