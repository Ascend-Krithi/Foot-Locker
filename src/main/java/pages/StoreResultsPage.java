package pages;

import core.BrowserUtils;
import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private final By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResults = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean areResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeCards);
    }

    public boolean isSpecificStoreAddressVisible(String address) {
        List<WebElement> addresses = driver.findElements(storeAddress);
        for (WebElement el : addresses) {
            if (el.getText().trim().contains(address)) {
                return true;
            }
        }
        return false;
    }

    public void setMyStore(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddress);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().contains(address)) {
                    WebElement btn = card.findElement(setMyStoreButton);
                    btn.click();
                    return;
                }
            }
        }
        throw new RuntimeException("Store with address not found: " + address);
    }

    public boolean isNoStoresFoundMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResults);
    }

    public boolean isConfirmationIndicatorDisplayed() {
        // Assume confirmation indicator is a class or text on the card after setting store
        // For demo, check if any card has 'My Store' or similar indicator
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            if (card.getText().contains("My Store") || card.getAttribute("class").toLowerCase().contains("selected")) {
                return true;
            }
        }
        return false;
    }
}
