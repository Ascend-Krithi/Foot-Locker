package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.LoginPage;
import com.fl.automation.pages.ProfilePage;

public class TS003_TC008_Marketplace extends BaseTest {
    
    @Test
    public void testProfileUpdate() {
        driver.get("https://marketplace.example.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("user@example.com", "UserPass123");
        
        driver.get("https://marketplace.example.com/profile");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.updateProfile("John Updated", "5551234567");
        profilePage.saveProfile();
        
        Assert.assertTrue(profilePage.isUpdateSuccessful(), "Profile update should be successful");
    }
}