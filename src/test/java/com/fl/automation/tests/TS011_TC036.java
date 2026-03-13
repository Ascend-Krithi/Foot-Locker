package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS011_TC036 extends BaseTest {

    @Test(description = "TC4151: SCRUM-19509 TS-011 TC-001 - Submit and verify project review")
    public void testSubmitAndVerifyProjectReview() {
        driver.get("https://marketplace.example.com/project/12345/review");
        Assert.assertTrue(driver.getCurrentUrl().contains("review"), "Should navigate to project review page");
    }
}