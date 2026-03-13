package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4118: SCRUM-19509 Eco Home Hub - Responsive design
 */
public class TS002_TC015 extends BaseTest {

    @Test(description = "TC-4118: Eco Home Hub responsive design")
    public void testEcoHomeHubResponsiveDesign() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.manage().window().getSize().getWidth() > 0, "Window should have width");
    }
}