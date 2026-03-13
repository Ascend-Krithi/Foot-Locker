package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.LoginPage;
import com.fl.automation.pages.DashboardPage;

public class TS003_TC009_Marketplace extends BaseTest {
    
    @Test
    public void testLogout() {
        driver.get("https://marketplace.example.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("user@example.com", "UserPass123");
        
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.clickLogout();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("login") || driver.getPageSource().contains("Login"), "User should be logged out");
    }
}