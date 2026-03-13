package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.AdminDashboardPage;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC003_AdminApproveVerifyMarketplace extends BaseTest {

    @Test(description = "TC_4110: SCRUM-19509 TS-002 TC-003 - Admin approve installer, verify visible in marketplace")
    public void testAdminApproveVerifyMarketplace() {
        AdminDashboardPage adminPage = new AdminDashboardPage(driver);
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        driver.get("https://eco-home-hub.example.com/admin");
        adminPage.login("admin", "admin123");
        adminPage.approveInstallerApplication();
        
        marketplacePage.navigateToMarketplace();
        boolean areInstallersVisible = marketplacePage.areProjectsDisplayed();
        Assert.assertTrue(areInstallersVisible, "Approved installer should be visible in marketplace");
    }
}