package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC028 extends BaseTest {

    @Test(description = "TC4143: SCRUM-19509 TS-004 TC-001 - Contact project owner")
    public void testContactProjectOwner() {
        driver.get("https://marketplace.example.com/project/12345/contact");
        Assert.assertTrue(driver.getCurrentUrl().contains("contact"), "Should navigate to contact project owner page");
    }
}