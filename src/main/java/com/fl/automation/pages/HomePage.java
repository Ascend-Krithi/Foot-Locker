package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By findStoreButton = By.xpath(
        "//a[contains(@href,'store-locator')] | " +
        "//a[contains(normalize-space(),'Find a Store')] | " +
        "//span[contains(normalize-space(),'Find a Store')] | " +
        "//button[contains(normalize-space(),'Find a Store')]"
    );

    private By storeDropdown = By.xpath(
        "//*[contains(@class,'StoreLocatorDropdown')]"
    );

    // ✅ Precise — targets the link INSIDE the StoreLocatorDropdown only
    private By selectMyStoreLink = By.xpath(
        "//*[contains(@class,'StoreLocatorDropdown')]//*[contains(normalize-space(),'Select my store')] | " +
        "//*[contains(@class,'StoreLocatorDropdown')]//a | " +
        "//*[contains(@class,'StoreLocatorDropdown')]//button"
    );

    private By cookieAcceptButton = By.id("onetrust-accept-btn-handler");

    // ✅ After clicking Select my store, the c-modals div should become active
    private By storeLocatorModal = By.xpath(
        "//*[contains(@class,'c-modals') and .//*[contains(@class,'StoreLocator')]] | " +
        "//*[contains(@class,'StoreLocatorModal')] | " +
        "//*[contains(@class,'store-locator-modal')] | " +
        "//*[@role='dialog'] | " +
        "//*[@aria-modal='true']"
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieBtn = wait.until(
                ExpectedConditions.elementToBeClickable(cookieAcceptButton)
            );
            cookieBtn.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieAcceptButton));
            System.out.println("[INFO] Cookie popup accepted.");
        } catch (Exception e) {
            System.out.println("[INFO] Cookie popup not present or already handled.");
        }
    }

    public void clickFindStore() {
        try {
            WebElement findStore = wait.until(
                ExpectedConditions.elementToBeClickable(findStoreButton)
            );
            safeClick(findStore);
            System.out.println("[INFO] Clicked 'Find a Store' button.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeDropdown));
            System.out.println("[INFO] Store locator dropdown is visible.");
        } catch (TimeoutException e) {
            throw new RuntimeException(
                "'Find a Store' button not clickable or dropdown did not appear. " +
                "URL: " + driver.getCurrentUrl(), e
            );
        }
    }

    public boolean isFindStoreLinkDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(findStoreButton)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectMyStoreLink)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        try {
            // ✅ Log everything inside the dropdown before clicking
            System.out.println("[DEBUG] Logging all elements inside StoreLocatorDropdown...");
            List<WebElement> dropdownChildren = driver.findElements(
                By.xpath("//*[contains(@class,'StoreLocatorDropdown')]//*")
            );
            for (WebElement el : dropdownChildren) {
                try {
                    System.out.println("[DEBUG] Dropdown child: tag=" + el.getTagName()
                        + " text='" + el.getText().trim().replace("\n", " ") + "'"
                        + " class='" + el.getAttribute("class") + "'"
                        + " href='" + el.getAttribute("href") + "'"
                        + " visible=" + el.isDisplayed());
                } catch (Exception ignored) {}
            }

            // ✅ Find and click the exact "Select my store" element inside dropdown
            WebElement selectMyStore = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(@class,'StoreLocatorDropdown')]//*[contains(normalize-space(text()),'Select my store') or contains(normalize-space(text()),'select my store')]")
            ));
            System.out.println("[INFO] Found 'Select my store' element: tag=" + selectMyStore.getTagName()
                + " text='" + selectMyStore.getText() + "'"
                + " href='" + selectMyStore.getAttribute("href") + "'");

            safeClick(selectMyStore);
            System.out.println("[INFO] Clicked 'Select my store' link.");

            // ✅ Wait 4s for modal/page to load
            Thread.sleep(4000);

            System.out.println("[DEBUG] URL after click: " + driver.getCurrentUrl());

            // ✅ Log ALL inputs after click
            driver.findElements(By.tagName("input")).forEach(el -> {
                try {
                    System.out.println("[DEBUG] Input after click: placeholder='"
                        + el.getAttribute("placeholder")
                        + "' id='" + el.getAttribute("id")
                        + "' class='" + el.getAttribute("class")
                        + "' type='" + el.getAttribute("type")
                        + "' visible=" + el.isDisplayed());
                } catch (Exception ignored) {}
            });

            // ✅ Log c-modals content
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script =
                "var modal = document.querySelector('.c-modals');" +
                "if (!modal) return 'c-modals not found';" +
                "return modal.innerHTML.substring(0, 500);";
            Object modalContent = js.executeScript(script);
            System.out.println("[DEBUG] c-modals innerHTML (first 500 chars): " + modalContent);

            System.out.println("[INFO] 'Select my store' click completed.");

        } catch (Exception e) {
            throw new RuntimeException(
                "Failed after clicking 'Select my store'. URL: " + driver.getCurrentUrl()
                + " | Error: " + e.getMessage(), e
            );
        }
    }

    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("[INFO] Click intercepted, falling back to JS click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
