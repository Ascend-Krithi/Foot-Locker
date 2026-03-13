package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MarketplacePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> SEARCH_INPUT = Arrays.asList(
            By.cssSelector("input[name='search']"),
            By.cssSelector("input[placeholder*='Search']"),
            By.xpath("//input[contains(@placeholder,'Search')]")
    );

    private static final List<By> SEARCH_BUTTON = Arrays.asList(
            By.cssSelector("button[type='submit']"),
            By.xpath("//button[contains(.,'Search')]"),
            By.cssSelector("button[class*='search']")
    );

    private static final List<By> CATEGORY_FILTER = Arrays.asList(
            By.cssSelector("select[name='category']"),
            By.cssSelector("div[class*='category-filter']"),
            By.xpath("//select[contains(@name,'category')]")
    );

    private static final List<By> PROJECT_CARDS = Arrays.asList(
            By.cssSelector("div[class*='project-card']"),
            By.cssSelector("div[data-qa='project']"),
            By.xpath("//div[contains(@class,'project')]")
    );

    private static final List<By> PROJECT_DETAILS = Arrays.asList(
            By.cssSelector("div[class*='project-details']"),
            By.xpath("//div[contains(@class,'details')]"),
            By.cssSelector("section[class*='details']")
    );

    private static final List<By> CONTACT_BUTTON = Arrays.asList(
            By.xpath("//button[contains(.,'Contact')]"),
            By.cssSelector("button[class*='contact']"),
            By.xpath("//a[contains(.,'Contact Owner')]")
    );

    private static final List<By> LOGIN_LINK = Arrays.asList(
            By.xpath("//a[contains(.,'Login')]"),
            By.cssSelector("a[href*='login']"),
            By.xpath("//a[contains(.,'Sign In')]")
    );

    private static final List<By> REGISTER_LINK = Arrays.asList(
            By.xpath("//a[contains(.,'Register')]"),
            By.cssSelector("a[href*='register']"),
            By.xpath("//a[contains(.,'Sign Up')]")
    );

    private static final List<By> DASHBOARD_LINK = Arrays.asList(
            By.xpath("//a[contains(.,'Dashboard')]"),
            By.cssSelector("a[href*='dashboard']"),
            By.xpath("//a[contains(.,'My Dashboard')]")
    );

    private static final List<By> APPLY_BUTTON = Arrays.asList(
            By.xpath("//button[contains(.,'Apply')]"),
            By.cssSelector("button[class*='apply']"),
            By.xpath("//button[contains(.,'Submit Application')]")
    );

    private static final List<By> PROFILE_LINK = Arrays.asList(
            By.xpath("//a[contains(.,'Profile')]"),
            By.cssSelector("a[href*='profile']"),
            By.xpath("//a[contains(.,'My Profile')]")
    );

    private static final List<By> LOGOUT_LINK = Arrays.asList(
            By.xpath("//a[contains(.,'Logout')]"),
            By.cssSelector("a[href*='logout']"),
            By.xpath("//button[contains(.,'Logout')]")
    );

    private static final List<By> REVIEW_SECTION = Arrays.asList(
            By.cssSelector("div[class*='review']"),
            By.xpath("//div[contains(@class,'reviews')]"),
            By.cssSelector("section[id='reviews']")
    );

    private static final List<By> CERTIFICATIONS_SECTION = Arrays.asList(
            By.cssSelector("div[class*='certification']"),
            By.xpath("//div[contains(.,'Certifications')]"),
            By.cssSelector("section[id='certifications']")
    );

    private static final List<By> SHARE_EMAIL_BUTTON = Arrays.asList(
            By.xpath("//button[contains(.,'Email')]"),
            By.cssSelector("button[class*='share-email']"),
            By.xpath("//a[contains(@class,'email-share')]")
    );

    private static final List<By> SHARE_SOCIAL_BUTTON = Arrays.asList(
            By.xpath("//button[contains(.,'Share')]"),
            By.cssSelector("button[class*='social-share']"),
            By.xpath("//a[contains(@class,'social')]")
    );

    private static final List<By> FAVORITE_BUTTON = Arrays.asList(
            By.xpath("//button[contains(.,'Favorite')]"),
            By.cssSelector("button[class*='favorite']"),
            By.xpath("//button[contains(@class,'save')]")
    );

    private static final List<By> HELP_LINK = Arrays.asList(
            By.xpath("//a[contains(.,'Help')]"),
            By.cssSelector("a[href*='help']"),
            By.xpath("//a[contains(.,'Support')]")
    );

    public MarketplacePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateToMarketplace() {
        driver.get("https://eco-home-hub.example.com/marketplace");
    }

    public boolean isPageLoaded() {
        try {
            WebElement searchInput = findElementWithFallback(SEARCH_INPUT);
            return searchInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void searchByKeyword(String keyword) {
        WebElement input = findElementWithFallback(SEARCH_INPUT);
        input.clear();
        input.sendKeys(keyword);
        WebElement button = findElementWithFallback(SEARCH_BUTTON);
        clickWithJsFallback(button);
    }

    public void filterByCategory(String category) {
        WebElement filter = findElementWithFallback(CATEGORY_FILTER);
        clickWithJsFallback(filter);
    }

    public boolean areProjectsDisplayed() {
        try {
            List<WebElement> projects = driver.findElements(PROJECT_CARDS.get(0));
            return projects != null && projects.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void viewProjectDetails() {
        List<WebElement> projects = driver.findElements(PROJECT_CARDS.get(0));
        if (projects.size() > 0) {
            clickWithJsFallback(projects.get(0));
        }
    }

    public boolean isProjectDetailsDisplayed() {
        try {
            WebElement details = findElementWithFallback(PROJECT_DETAILS);
            return details.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void contactProjectOwner() {
        WebElement button = findElementWithFallback(CONTACT_BUTTON);
        clickWithJsFallback(button);
    }

    public void clickLogin() {
        WebElement link = findElementWithFallback(LOGIN_LINK);
        clickWithJsFallback(link);
    }

    public void clickRegister() {
        WebElement link = findElementWithFallback(REGISTER_LINK);
        clickWithJsFallback(link);
    }

    public void clickDashboard() {
        WebElement link = findElementWithFallback(DASHBOARD_LINK);
        clickWithJsFallback(link);
    }

    public void applyToProject() {
        WebElement button = findElementWithFallback(APPLY_BUTTON);
        clickWithJsFallback(button);
    }

    public void clickProfile() {
        WebElement link = findElementWithFallback(PROFILE_LINK);
        clickWithJsFallback(link);
    }

    public void clickLogout() {
        WebElement link = findElementWithFallback(LOGOUT_LINK);
        clickWithJsFallback(link);
    }

    public boolean isReviewSectionDisplayed() {
        try {
            WebElement section = findElementWithFallback(REVIEW_SECTION);
            return section.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCertificationsSectionDisplayed() {
        try {
            WebElement section = findElementWithFallback(CERTIFICATIONS_SECTION);
            return section.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void shareViaEmail() {
        WebElement button = findElementWithFallback(SHARE_EMAIL_BUTTON);
        clickWithJsFallback(button);
    }

    public void shareViaSocial() {
        WebElement button = findElementWithFallback(SHARE_SOCIAL_BUTTON);
        clickWithJsFallback(button);
    }

    public void saveToFavorites() {
        WebElement button = findElementWithFallback(FAVORITE_BUTTON);
        clickWithJsFallback(button);
    }

    public void clickHelp() {
        WebElement link = findElementWithFallback(HELP_LINK);
        clickWithJsFallback(link);
    }

    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}