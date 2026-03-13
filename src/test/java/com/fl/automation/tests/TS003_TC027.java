package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC027 extends BaseTest {

    @Test(description = "TC4142: SCRUM-19509 TS-003 TC-001 - Project details page verification")
    public void testProjectDetailsPageVerification() {
        driver.get("https://marketplace.example.com/project/12345");
        Assert.assertTrue(driver.getCurrentUrl().contains("project"), "Should navigate to project details page");
    }
}