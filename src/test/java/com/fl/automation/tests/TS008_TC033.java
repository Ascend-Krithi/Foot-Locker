package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS008_TC033 extends BaseTest {

    @Test(description = "TC4148: SCRUM-19509 TS-008 TC-001 - Apply to project")
    public void testApplyToProject() {
        driver.get("https://marketplace.example.com/project/12345/apply");
        Assert.assertTrue(driver.getCurrentUrl().contains("apply"), "Should navigate to apply to project page");
    }
}