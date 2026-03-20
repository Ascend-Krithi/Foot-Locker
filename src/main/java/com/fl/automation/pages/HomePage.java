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

    private By findStoreButton = By.xpath("//button[contains(@class, 'HeaderButton') and contains(., 'Find a Store')]");
    private By findStoreLink = By.xpath("//a[contains(., 'Find a Store')]");
    private By findStorePopup = By.xpath("//div[contains(@class, 'StoreLocatorPopup')]");
    private By selectMyStoreLink = By.xpath("//a[contains(text(), 'Select My Store')]");
    private By storeLocatorModal = By.xpath("//div[contains(@class, 'StoreLocatorModal') or contains(@class, 'store-locator-modal')]");
    private By locationTextbox = By.xpath("//input[@placeholder='City, State or Zip Code' or contains(@placeholder, 'location') or @name='location']");
    private By searchButton = By.xpath("//button[contains(text(), 'Search for Stores') or contains(., 'Search')]");
    private By storeResults = By.xpath("//div[contains(@class, 'StoreResult') or contains(@class, 'store-result')]");
    private By selectedStoreName = By.xpath("//div[contains(@class, 'SelectedStore')]//span[contains(@class, 'StoreName')]");
    private By popupMessage = By.xpath("//div[contains(., 'Choose a preferred store')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void navigateToHomePage() {
        driver.get("https://www.footlocker.com");
        waitForPageLoad();
    }

    public void navigateToSneakersPage() {
        driver.get("https://www.footlocker.com/sneakers");
        waitForPageLoad();
    }

    public boolean isOnFootLockerDomain() {
        return driver.getCurrentUrl().contains("footlocker.com");
    }

    public void clickFindStoreButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findStoreButton));
            try {
                element.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        } catch (Exception e) {
            try {
                WebElement linkElement = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", linkElement);
            } catch (Exception ex) {
                throw new RuntimeException("Unable to click Find Store button/link", ex);
            }
        }
    }

    public boolean isFindStorePopupDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(findStorePopup));
            return driver.findElement(findStorePopup).isDisplayed();
        } catch (Exception e) {
            try {
                return driver.findElement(popupMessage).isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreLink));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStoreLink() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink));
            try {
                element.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Unable to click Select My Store link", e);
        }
    }

    public boolean isStoreLocatorModalDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeLocatorModal));
            return driver.findElement(storeLocatorModal).isDisplayed();
        } catch (Exception e) {
            try {
                By alternateModal = By.xpath("//div[contains(@class, 'modal') and contains(., 'Store')]");
                return driver.findElement(alternateModal).isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public boolean isLocationTextboxDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox));
            return driver.findElement(locationTextbox).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
            return driver.findElement(searchButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox));
            element.clear();
            element.sendKeys(location);
        } catch (Exception e) {
            throw new RuntimeException("Unable to enter location in textbox", e);
        }
    }

    public void clickSearchButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            try {
                element.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
            Thread.sleep(3000);
        } catch (Exception e) {
            throw new RuntimeException("Unable to click Search button", e);
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResults));
            return driver.findElements(storeResults).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSelectedStoreName() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedStoreName));
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }

    private void waitForPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }
}