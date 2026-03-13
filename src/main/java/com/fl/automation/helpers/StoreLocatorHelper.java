package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final By STORE_RESULT_CARDS = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private static final By STORE_ADDRESS = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private static final By SET_MY_STORE_BTN = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public boolean areStoreResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULT_CARDS));
            List<WebElement> storeCards = driver.findElements(STORE_RESULT_CARDS);
            return !storeCards.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public int getStoreResultsCount() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULT_CARDS));
            return driver.findElements(STORE_RESULT_CARDS).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isStoreAddressVisible(String expectedAddress) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULT_CARDS));
            List<WebElement> storeCards = driver.findElements(STORE_RESULT_CARDS);
            
            for (WebElement card : storeCards) {
                try {
                    WebElement addressElement = card.findElement(STORE_ADDRESS);
                    String actualAddress = addressElement.getText().trim();
                    if (actualAddress.contains(expectedAddress) || expectedAddress.contains(actualAddress)) {
                        return true;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getStoreAddress(String partialAddress) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULT_CARDS));
            List<WebElement> storeCards = driver.findElements(STORE_RESULT_CARDS);
            
            for (WebElement card : storeCards) {
                try {
                    WebElement addressElement = card.findElement(STORE_ADDRESS);
                    String actualAddress = addressElement.getText().trim();
                    if (actualAddress.contains(partialAddress)) {
                        return actualAddress;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void setMyStoreByAddress(String targetAddress) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULT_CARDS));
            List<WebElement> storeCards = driver.findElements(STORE_RESULT_CARDS);
            
            for (WebElement card : storeCards) {
                try {
                    WebElement addressElement = card.findElement(STORE_ADDRESS);
                    String actualAddress = addressElement.getText().trim();
                    
                    if (actualAddress.contains(targetAddress)) {
                        WebElement setStoreButton = card.findElement(SET_MY_STORE_BTN);
                        clickElement(setStoreButton);
                        return;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            throw new RuntimeException("Store with address '" + targetAddress + "' not found");
        } catch (Exception e) {
            throw new RuntimeException("Failed to set store: " + e.getMessage(), e);
        }
    }

    public boolean isStoreSetConfirmationDisplayed() {
        try {
            By confirmationIndicators = By.cssSelector("[class*='confirmation'], [class*='success'], [data-qa*='confirmation'], .toast, .notification");
            wait.until(ExpectedConditions.presenceOfElementLocated(confirmationIndicators));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreDisplayedInHeader(String storeIdentifier) {
        try {
            By headerStoreIndicators = By.xpath("//header//*[contains(text(),'" + storeIdentifier + "')]" +
                " | //*[@class='header' or @id='header']//*[contains(text(),'" + storeIdentifier + "')]" +
                " | //*[contains(@class,'store-indicator')]//*[contains(text(),'" + storeIdentifier + "')]");
            
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            shortWait.until(ExpectedConditions.presenceOfElementLocated(headerStoreIndicators));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyStorePreferencePersists(String storeIdentifier) {
        try {
            return isStoreDisplayedInHeader(storeIdentifier);
        } catch (Exception e) {
            return false;
        }
    }

    private void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                js.executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                throw new RuntimeException("Failed to click element", jsException);
            }
        }
    }
}