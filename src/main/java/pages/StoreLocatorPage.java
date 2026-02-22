package pages;

import core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private final WebDriver driver;
    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By searchInput = By.cssSelector("input[type='search']");
    private final By searchInputAlt1 = By.cssSelector("input[name='q']");
    private final By searchInputAlt2 = By.cssSelector("input[aria-label*='Search']");
    private final By searchInputAlt3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By searchButtonAlt = By.xpath("//button[contains(.,'Search')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        try {
            WaitUtils.waitForClickable(driver, selectMyStoreLink).click();
        } catch (Exception e) {
            WaitUtils.waitForClickable(driver, selectMyStoreButton).click();
        }
    }

    public void enterSearchLocation(String location) {
        WebElement input;
        try {
            input = WaitUtils.waitForVisible(driver, searchInput);
        } catch (Exception e1) {
            try {
                input = WaitUtils.waitForVisible(driver, searchInputAlt1);
            } catch (Exception e2) {
                try {
                    input = WaitUtils.waitForVisible(driver, searchInputAlt2);
                } catch (Exception e3) {
                    input = WaitUtils.waitForVisible(driver, searchInputAlt3);
                }
            }
        }
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchButton() {
        try {
            WaitUtils.waitForClickable(driver, searchButton).click();
        } catch (Exception e) {
            WaitUtils.waitForClickable(driver, searchButtonAlt).click();
        }
    }
}
