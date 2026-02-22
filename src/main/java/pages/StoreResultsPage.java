package pages;

import core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private final WebDriver driver;
    private final By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButtonInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");
    private final By popupMessage = By.cssSelector(".popup-message, [class*='popup'], [class*='message']");
    private final By confirmationIndicator = By.cssSelector("[class*='confirmation'], [class*='success'], [class*='selected-store']");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopupDisplayed() {
        try {
            return WaitUtils.waitForVisible(driver, popupMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkPresent() {
        try {
            driver.findElement(By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")).isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLocationTextboxPresent() {
        try {
            driver.findElement(By.cssSelector("input[type='search']")).isDisplayed();
            return true;
        } catch (Exception e1) {
            try {
                driver.findElement(By.cssSelector("input[name='q']")).isDisplayed();
                return true;
            } catch (Exception e2) {
                try {
                    driver.findElement(By.cssSelector("input[aria-label*='Search']")).isDisplayed();
                    return true;
                } catch (Exception e3) {
                    driver.findElement(By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")).isDisplayed();
                    return true;
                }
            }
        }
    }

    public boolean isSearchButtonPresent() {
        try {
            driver.findElement(By.cssSelector("button[type='submit']")).isDisplayed();
            return true;
        } catch (Exception e1) {
            try {
                driver.findElement(By.xpath("//button[contains(.,'Search')]")).isDisplayed();
                return true;
            } catch (Exception e2) {
                return false;
            }
        }
    }

    public List<WebElement> getStoreResultCards() {
        return WaitUtils.waitForAllVisible(driver, storeResultCards);
    }

    public boolean isStoreResultsDisplayed() {
        try {
            return getStoreResultCards().size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoStoresFoundMessageDisplayed() {
        try {
            return WaitUtils.waitForVisible(driver, emptyResultsMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreAddressPresent(String address) {
        List<WebElement> cards = getStoreResultCards();
        for (WebElement card : cards) {
            try {
                WebElement addr = card.findElement(storeAddressInsideCard);
                if (addr.getText().trim().contains(address)) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public void setMyStoreByAddress(String address) {
        List<WebElement> cards = getStoreResultCards();
        for (WebElement card : cards) {
            try {
                WebElement addr = card.findElement(storeAddressInsideCard);
                if (addr.getText().trim().contains(address)) {
                    WebElement btn = card.findElement(setMyStoreButtonInsideCard);
                    btn.click();
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Store with address not found: " + address);
    }

    public boolean isConfirmationIndicatorDisplayed() {
        try {
            return WaitUtils.waitForVisible(driver, confirmationIndicator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
