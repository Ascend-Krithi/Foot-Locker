package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC001 extends BaseTest {

    @Test
    public void validateFindStorePopup() {

        HomePage home = new HomePage(driver);

        // Stabilize landing page
        home.acceptCookiesIfPresent();
        home.closeFlxRewardsIfPresent();

        // Click Find a Store
        home.clickFindAStore();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        boolean popupVisible = false;

        try {

            // Primary validation
            popupVisible = wait.until(driver ->
                    driver.findElement(
                            By.xpath("//*[contains(text(),'Choose a preferred store')]")
                    ).isDisplayed()
            );

        } catch (Exception e) {

            try {

                // Fallback validation
                popupVisible = wait.until(driver ->
                        driver.findElement(
                                By.xpath("//*[contains(text(),'Select my store')]")
                        ).isDisplayed()
                );

            } catch (Exception ignored) {}
        }

        Assert.assertTrue(
                popupVisible,
                "Find a Store popup with 'Select my store' link did not appear."
        );
    }
}