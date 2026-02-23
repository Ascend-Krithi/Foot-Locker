package pages;

import core.BrowserUtils;
import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private final By selectMyStore = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreFallback = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By searchInput = By.cssSelector("input[type='search']");
    private final By searchInputFallback1 = By.cssSelector("input[name='q']");
    private final By searchInputFallback2 = By.cssSelector("input[aria-label*='Search']");
    private final By searchInputFallback3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By searchButton = By.cssSelector("button[type='submit'], button[aria-label*='Search']");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, selectMyStore)) {
            BrowserUtils.click(driver, selectMyStore);
        } else {
            BrowserUtils.click(driver, selectMyStoreFallback);
        }
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, searchInput)) {
            BrowserUtils.type(driver, searchInput, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputFallback1)) {
            BrowserUtils.type(driver, searchInputFallback1, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputFallback2)) {
            BrowserUtils.type(driver, searchInputFallback2, location);
        } else {
            BrowserUtils.type(driver, searchInputFallback3, location);
        }
    }

    public void clickSearch() {
        BrowserUtils.click(driver, searchButton);
    }

    public boolean isLocationInputDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchInput) ||
               BrowserUtils.isDisplayed(driver, searchInputFallback1) ||
               BrowserUtils.isDisplayed(driver, searchInputFallback2) ||
               BrowserUtils.isDisplayed(driver, searchInputFallback3);
    }

    public boolean isSearchButtonEnabled() {
        try {
            return DriverFactory.getDriver().findElement(searchButton).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}
