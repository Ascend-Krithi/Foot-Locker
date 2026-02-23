package pages;

import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;
    private By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By locationInput = By.cssSelector("input[type='search']");
    private By locationInputName = By.cssSelector("input[name='q']");
    private By locationInputAria = By.cssSelector("input[aria-label*='Search']");
    private By locationInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.xpath("//button[contains(.,'Search')]");
    private By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isPopupMessageDisplayed() {
        return driver.findElements(popupMessage).size() > 0;
    }

    public boolean isSelectMyStoreLinkVisible() {
        return driver.findElements(selectMyStoreLink).size() > 0;
    }

    public void clickSelectMyStore() {
        if (driver.findElements(selectMyStoreLink).size() > 0) {
            driver.findElement(selectMyStoreLink).click();
        } else {
            driver.findElement(selectMyStoreButton).click();
        }
    }

    public boolean isLocationInputVisible() {
        return driver.findElements(locationInput).size() > 0 ||
               driver.findElements(locationInputName).size() > 0 ||
               driver.findElements(locationInputAria).size() > 0 ||
               driver.findElements(locationInputPlaceholder).size() > 0;
    }

    public void enterLocation(String location) {
        if (driver.findElements(locationInput).size() > 0) {
            driver.findElement(locationInput).clear();
            driver.findElement(locationInput).sendKeys(location);
        } else if (driver.findElements(locationInputName).size() > 0) {
            driver.findElement(locationInputName).clear();
            driver.findElement(locationInputName).sendKeys(location);
        } else if (driver.findElements(locationInputAria).size() > 0) {
            driver.findElement(locationInputAria).clear();
            driver.findElement(locationInputAria).sendKeys(location);
        } else {
            driver.findElement(locationInputPlaceholder).clear();
            driver.findElement(locationInputPlaceholder).sendKeys(location);
        }
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public boolean isSearchButtonEnabled() {
        return driver.findElement(searchButton).isEnabled();
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return driver.findElements(emptyResultsMsg).size() > 0;
    }

    public void clickSearchButtonIfEnabled() {
        if (isSearchButtonEnabled()) {
            clickSearchButton();
        }
    }
}
