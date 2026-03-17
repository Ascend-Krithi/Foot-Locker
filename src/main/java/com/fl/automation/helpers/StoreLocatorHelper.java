package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void waitForStoreLocatorToLoad() {
        int retries = 3;
        for (int i = 1; i <= retries; i++) {
            try {
                System.out.println("[INFO] Waiting for store locator modal... Attempt: " + i);

                List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
                System.out.println("[DEBUG] Total iframes found: " + iframes.size());
                for (int j = 0; j < iframes.size(); j++) {
                    WebElement f = iframes.get(j);
                    System.out.println("[DEBUG] iframe[" + j + "] id='" + f.getAttribute("id")
                        + "' src='" + f.getAttribute("src")
                        + "' title='" + f.getAttribute("title") + "'");
                }

                boolean foundInIframe = false;
                for (int j = 0; j < iframes.size(); j++) {
                    try {
                        driver.switchTo().frame(iframes.get(j));
                        List<WebElement> inputs = driver.findElements(By.tagName("input"));
                        System.out.println("[DEBUG] Inputs inside iframe[" + j + "]: " + inputs.size());
                        for (WebElement inp : inputs) {
                            System.out.println("[DEBUG]   -> placeholder='" + inp.getAttribute("placeholder")
                                + "' id='" + inp.getAttribute("id")
                                + "' name='" + inp.getAttribute("name")
                                + "' visible=" + inp.isDisplayed());
                        }
                        if (!inputs.isEmpty()) {
                            try {
                                WebElement input = new WebDriverWait(driver, Duration.ofSeconds(10))
                                    .until(ExpectedConditions.visibilityOfElementLocated(locationInput));
                                if (input.isDisplayed()) {
                                    System.out.println("[INFO] Store locator input found inside iframe[" + j + "]");
                                    foundInIframe = true;
                                    return;
                                }
                            } catch (Exception inner) {
                                System.out.println("[DEBUG] locationInput not matched in iframe[" + j + "]");
                            }
                        }
                        driver.switchTo().defaultContent();
                    } catch (Exception ex) {
                        System.out.println("[DEBUG] iframe[" + j + "] error: " + ex.getMessage());
                        driver.switchTo().defaultContent();
                    }
                }

                if (!foundInIframe) {
                    System.out.println("[DEBUG] Trying Shadow DOM piercing via JavaScript...");
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    String script =
                        "var allInputs = [];" +
                        "function findInputs(root) {" +
                        "  root.querySelectorAll('input').forEach(function(el) {" +
                        "    allInputs.push(el.placeholder + '|' + el.id + '|' + el.name + '|' + el.type);" +
                        "  });" +
                        "  root.querySelectorAll('*').forEach(function(el) {" +
                        "    if (el.shadowRoot) findInputs(el.shadowRoot);" +
                        "  });" +
                        "}" +
                        "findInputs(document);" +
                        "return allInputs;";
                    @SuppressWarnings("unchecked")
                    List<String> shadowInputs = (List<String>) js.executeScript(script);
                    System.out.println("[DEBUG] Inputs found via Shadow DOM pierce: " + shadowInputs.size());
                    for (String s : shadowInputs) {
                        System.out.println("[DEBUG] Shadow input: " + s);
                    }
                }

            } catch (Exception e) {
                System.out.println("[WARN] Retry loading store locator... Attempt: " + i + " | Error: " + e.getMessage());
                driver.switchTo().defaultContent();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }

        throw new RuntimeException("Store locator input load failed");
    }

    public void enterLocation(String location) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
        input.clear();
        input.sendKeys(location);
        input.sendKeys(Keys.ENTER);
        System.out.println("[INFO] Entered location: " + location);
    }

    public void clickSearchButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        System.out.println("[INFO] Clicked search button");
    }

    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(storeResults));
            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSpecificStoreDisplayed(String storeText) {
        try {
            return driver.findElement(By.xpath("//*[contains(text(),'" + storeText + "')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String storeText) {
        try {
            WebElement store = driver.findElement(
                By.xpath("//*[contains(text(),'" + storeText + "')]/ancestor::div"));
            WebElement btn = store.findElement(By.xpath(".//button[contains(.,'Set')]"));
            btn.click();
            System.out.println("[INFO] Clicked Set My Store for: " + storeText);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Set My Store for: " + storeText);
        }
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLocationSearchInputDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
