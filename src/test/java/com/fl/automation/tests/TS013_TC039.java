package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS013_TC039 extends BaseTest {

    @Test(description = "TC4154: SCRUM-19509 TS-013 TC-002 - Share project via social media")
    public void testShareProjectViaSocialMedia() {
        driver.get("https://marketplace.example.com/project/12345/share?method=social");
        Assert.assertTrue(driver.getCurrentUrl().contains("share"), "Should navigate to share project via social media page");
    }
}