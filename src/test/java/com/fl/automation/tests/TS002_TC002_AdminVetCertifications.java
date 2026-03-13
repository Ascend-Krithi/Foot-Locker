package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.AdminDashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC002_AdminVetCertifications extends BaseTest {

    @Test(description = "TC_4109: SCRUM-19509 TS-002 TC-002 - Admin vet certifications and approve installer")
    public void testAdminVetCertifications() {
        AdminDashboardPage adminPage = new AdminDashboardPage(driver);
        
        driver.get("https://eco-home-hub.example.com/admin");
        adminPage.login("admin", "admin123");
        
        boolean isCertificationsSectionDisplayed = adminPage.isCertificationsSectionDisplayed();
        Assert.assertTrue(isCertificationsSectionDisplayed, "Certifications section should be displayed");
        
        adminPage.approveInstallerApplication();
    }
}