package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS020_TC001_ViewEcoCertifications extends BaseTest {

    @Test(description = "TC_4152: SCRUM-19509 TS-012 TC-001 - View eco-certifications")
    public void testViewEcoCertifications() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.viewProjectDetails();
        
        boolean isCertificationsSectionDisplayed = marketplacePage.isCertificationsSectionDisplayed();
        Assert.assertTrue(isCertificationsSectionDisplayed, "Certifications section should be displayed");
    }
}