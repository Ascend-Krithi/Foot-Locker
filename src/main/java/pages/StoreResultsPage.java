package pages;

import core.BrowserUtils;
import core.WaitUtils;
import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address");
    private By setMyStoreBtn = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store')]");
    private By emptyResults = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isResultsDisplayed() {
        List<WebElement> results = driver.findElements(storeResultCards);
        return results.size() > 0;
    }

    public boolean isNoResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver.findElement(emptyResults));
    }

    public boolean isStoreAddressVisible(String address) {
        List<WebElement> addresses = driver.findElements(storeAddress);
        for (WebElement el : addresses) {
            if (BrowserUtils.getText(el).contains(address)) {
                return true;
            }
        }
        return false;
    }

    public void setMyStore(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddress);
            for (WebElement el : addresses) {
                if (BrowserUtils.getText(el).contains(address)) {
                    WebElement btn = card.findElement(setMyStoreBtn);
                    WaitUtils.waitForElementToBeClickable(driver, btn);
                    BrowserUtils.click(btn);
                    return;
                }
            }
        }
    }

    public boolean isStoreSet(String address) {
        // For demonstration, assume confirmation indicator is a class 'selected' on card
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddress);
            for (WebElement el : addresses) {
                if (BrowserUtils.getText(el).contains(address) && card.getAttribute("class").contains("selected")) {
                    return true;
                }
            }
        }
        return false;
    }
}
