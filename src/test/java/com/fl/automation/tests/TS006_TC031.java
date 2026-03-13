package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC031 extends BaseTest {

    @Test(description = "TC4146: SCRUM-19509 TS-006 TC-001 - User registration")
    public void testUserRegistration() {
        driver.get("https://marketplace.example.com/register");
        Assert.assertTrue(driver.getCurrentUrl().contains("register"), "Should navigate to user registration page");
    }
}