package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplaceHomePage;
import com.fl.automation.pages.LoginPage;

public class TS003_TC005_Marketplace extends BaseTest {
    
    @Test
    public void testMarketplaceLogin() {
        driver.get("https://marketplace.example.com");
        MarketplaceHomePage homePage = new MarketplaceHomePage(driver);
        homePage.clickLogin();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("user@example.com", "UserPass123");
        
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
    }
}