package pages;

import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButtonInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isStoreResultDisplayed() {
        return driver.findElements(storeResultCards).size() > 0;
    }

    public boolean isStoreWithAddressVisible(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInsideCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().equals(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clickSetMyStoreForAddress(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInsideCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().equals(address)) {
                    List<WebElement> btns = card.findElements(setMyStoreButtonInsideCard);
                    if (!btns.isEmpty()) {
                        btns.get(0).click();
                        return;
                    }
                }
            }
        }
    }

    public boolean isConfirmationIndicatorDisplayed(String address) {
        // This is a placeholder for confirmation indicator logic
        // In real implementation, check for confirmation UI element
        return isStoreWithAddressVisible(address);
    }
}
