package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getStoreCards() {
        return WaitUtils.waitForElementsVisible(driver, storeResultCards);
    }

    public List<String> getStoreAddresses() {
        List<WebElement> cards = getStoreCards();
        List<String> addresses = new ArrayList<>();
        for (WebElement card : cards) {
            try {
                WebElement addr = card.findElement(storeAddress);
                addresses.add(addr.getText());
            } catch (Exception ignored) {}
        }
        return addresses;
    }

    public void setFirstStoreAsMyStore() {
        List<WebElement> cards = getStoreCards();
        for (WebElement card : cards) {
            try {
                WebElement btn = card.findElement(setMyStoreButton);
                btn.click();
                return;
            } catch (Exception ignored) {}
        }
        // Try global selectors if not found in cards
        try {
            WebElement link = WaitUtils.waitForElementClickable(driver, selectMyStoreLink);
            link.click();
        } catch (Exception e1) {
            WebElement btn = WaitUtils.waitForElementClickable(driver, selectMyStoreButton);
            btn.click();
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        try {
            WebElement msg = WaitUtils.waitForElementVisible(driver, emptyResultsMessage);
            return msg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
