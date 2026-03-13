package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS009_TC034 extends BaseTest {

    @Test(description = "TC4149: SCRUM-19509 TS-009 TC-001 - Edit user profile")
    public void testEditUserProfile() {
        driver.get("https://marketplace.example.com/profile/edit");
        Assert.assertTrue(driver.getCurrentUrl().contains("profile") && driver.getCurrentUrl().contains("edit"), 
            "Should navigate to edit user profile page");
    }
}