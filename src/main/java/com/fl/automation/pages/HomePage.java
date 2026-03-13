package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    private static final By FIND_A_STORE_LINK = By.linkText("Find a Store");
    private static final By FIND_A_STORE_LINK_ALT1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private static final By FIND_A_STORE_LINK_ALT2 = By.xpath("//header//a[contains(.,'Find a Store')]");
    
    private static final By LOGIN_LINK = By.linkText("Login");
    private static final By LOGIN_LINK_ALT = By.cssSelector("a[href*='login']");
    
    private static final By REGISTER_LINK = By.linkText("Register");
    private static final By REGISTER_LINK_ALT = By.cssSelector("a[href*='register']");
    
    private static final By SEARCH_BOX = By.cssSelector("input[type='search']");
    private static final By SEARCH_BOX_ALT = By.cssSelector("input[placeholder*='Search']");
    
    private static final By MARKETPLACE_LINK = By.linkText("Marketplace");
    private static final By MARKETPLACE_LINK_ALT = By.cssSelector("a[href*='marketplace']");
    
    private static final By ECO_HUB_LINK = By.linkText("Eco Home Hub");
    private static final By ECO_HUB_LINK_ALT = By.cssSelector("a[href*='eco-home-hub']");
    
    private static final By USER_DASHBOARD_LINK = By.linkText("Dashboard");
    private static final By USER_DASHBOARD_LINK_ALT = By.cssSelector("a[href*='dashboard']");
    
    private static final By HELP_SUPPORT_LINK = By.linkText("Help & Support");
    private static final By HELP_SUPPORT_LINK_ALT = By.cssSelector("a[href*='help']");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }
    
    public void navigateToHomePage(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.urlContains(url));
    }
    
    public void clickFindAStore() {
        WebElement findAStoreLink = findElementWithFallback(
            FIND_A_STORE_LINK, 
            FIND_A_STORE_LINK_ALT1, 
            FIND_A_STORE_LINK_ALT2
        );
        clickWithJSFallback(findAStoreLink);
    }
    
    public void clickLogin() {
        WebElement loginLink = findElementWithFallback(LOGIN_LINK, LOGIN_LINK_ALT);
        clickWithJSFallback(loginLink);
    }
    
    public void clickRegister() {
        WebElement registerLink = findElementWithFallback(REGISTER_LINK, REGISTER_LINK_ALT);
        clickWithJSFallback(registerLink);
    }
    
    public void searchForItem(String searchTerm) {
        WebElement searchBox = findElementWithFallback(SEARCH_BOX, SEARCH_BOX_ALT);
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }
    
    public void clickMarketplace() {
        WebElement marketplaceLink = findElementWithFallback(MARKETPLACE_LINK, MARKETPLACE_LINK_ALT);
        clickWithJSFallback(marketplaceLink);
    }
    
    public void clickEcoHomeHub() {
        WebElement ecoHubLink = findElementWithFallback(ECO_HUB_LINK, ECO_HUB_LINK_ALT);
        clickWithJSFallback(ecoHubLink);
    }
    
    public void clickUserDashboard() {
        WebElement dashboardLink = findElementWithFallback(USER_DASHBOARD_LINK, USER_DASHBOARD_LINK_ALT);
        clickWithJSFallback(dashboardLink);
    }
    
    public void clickHelpSupport() {
        WebElement helpLink = findElementWithFallback(HELP_SUPPORT_LINK, HELP_SUPPORT_LINK_ALT);
        clickWithJSFallback(helpLink);
    }
    
    public boolean isHomePageLoaded() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(FIND_A_STORE_LINK));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private WebElement findElementWithFallback(By... locators) {
        for (By locator : locators) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element != null) {
                    return element;
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }
    
    private void clickWithJSFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}