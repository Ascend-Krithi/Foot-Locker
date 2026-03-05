package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Step 1 — Click "Find a Store"
        home.clickFindAStore();

        // Step 2 — Validate popup message
        By choosePreferredStore = By.xpath("//*[contains(text(),'Choose a preferred store') or contains(text(),'make shopping easier')]");
        WebElement popupText = wait.until(ExpectedConditions.visibilityOfElementLocated(choosePreferredStore));

        Assert.assertTrue(
                "Expected text 'Choose a preferred store to make shopping easier.' did not appear."
        );

        // Step 3 — Click "Select my store"
        By selectMyStore = By.xpath("//*[contains(text(),'Select my store') or contains(text(),'Select My Store')]");
        WebElement selectStoreBtn = wait.until(ExpectedConditions.elementToBeClickable(selectMyStore));
        selectStoreBtn.click();
    }
}
