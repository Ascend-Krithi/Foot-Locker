package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.LoginPage;
import com.fl.automation.pages.AdminDashboardPage;

public class TS002_TC004_EcoHomeHub extends BaseTest {
    
    @Test
    public void testAdminDashboard() {
        driver.get("https://ecohomehub.example.com/admin/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin@example.com", "AdminPass123");
        
        AdminDashboardPage dashboard = new AdminDashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardDisplayed(), "Admin dashboard should be displayed");
        Assert.assertTrue(dashboard.areAdminFeaturesVisible(), "Admin features should be visible");
    }
}