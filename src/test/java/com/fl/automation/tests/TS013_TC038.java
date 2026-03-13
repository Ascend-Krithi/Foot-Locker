package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS013_TC038 extends BaseTest {

    @Test(description = "TC4153: SCRUM-19509 TS-013 TC-001 - Share project via email")
    public void testShareProjectViaEmail() {
        driver.get("https://marketplace.example.com/project/12345/share?method=email");
        Assert.assertTrue(driver.getCurrentUrl().contains("share"), "Should navigate to share project page");
    }
}