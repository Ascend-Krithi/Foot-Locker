package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // 🔥 Broadened locator
    private By locationInput = By.xpath(
        "//input[" +
            "contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'location') or " +
            "contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'city') or " +
            "contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'zip') or " +
            "contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'postal') or " +
            "contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'address') or " +
            "contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'state') or " +
            "contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'store') or " +
            "contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'location') or " +
            "contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'store') or " +
            "contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search') or " +
            "contains(@id,'store') or contains(@id,'location') or " +
            "contains(@name,'store') or contains(@name,'location')" +
        "]"
    );

    private By searchButton = By.xpath("//button[.//text()[contains(.,'Search')]]");
    private By storeResults = By.xpath("//div[contains(@class,'store') or contains(@class,'result')]");
    private By setMyStoreButton = By.xpath("//button[contains(.,'Set') or contains(.,'My Store')]");
    private By confirmationMessage = By.xpath("//*[contains(text(),'store') and contains(text(),'set')]");

    // ✅ Wait for popup — with iframe + Shadow DOM detection
    public void waitForStoreLocatorToLoad() {
        int retries = 3;

        for (int i = 1; i <= retries; i++) {
            try {
                System.out.println("[INFO] Waiting for store locator modal... Attempt: " + i);

                // Step 1: Log all iframes on page
                List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
                System.out.println("[DEBUG] Total iframes found: " + iframes.size());
                for (int j = 0; j < iframes.size(); j++) {
                    WebElement f = iframes.get(j);
                    System.out.println("[DEBUG] iframe[" + j + "] id='" + f.getAttribute("id") +
                        "' src='" + f.getAttribute("src") +
                        "' title='" + f.getAttribute("title") + "'");
                }

                // Step 2: Try switching into each iframe to find the store input
                boolean foundInIframe = false;
                for (int j = 0; j < iframes.size(); j++) {
                    try {
                        driver.switchTo().frame(iframes.get(j));
                        List<WebElement> inputs = driver.findElements(By.tagName("input"));
                        System.out.println("[DEBUG] Inputs inside iframe[" + j + "]: " + inputs.size());
                        for (WebElement inp : inputs) {
                            System.out.println("[DEBUG]   -> placeholder='" + inp.getAttribute("placeholder") +
                                "' id='" + inp.getAttribute("id"
