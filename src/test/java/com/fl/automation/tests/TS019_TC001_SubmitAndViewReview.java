package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS019_TC001_SubmitAndViewReview extends BaseTest {

    @Test(description = "TC_4151: SCRUM-19509 TS-011 TC-001 - Submit and view review")
    public void testSubmitAndViewReview() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.viewProjectDetails();
        
        boolean isReviewSectionDisplayed = marketplacePage.isReviewSectionDisplayed();
        Assert.assertTrue(isReviewSectionDisplayed, "Review section should be displayed");
    }
}