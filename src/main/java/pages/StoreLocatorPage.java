package pages;

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserUtils;
import utils.WaitUtils;

public class StoreLocatorPage {
    private WebDriver driver;

    // Popup container: try to find the popup below the Find a Store button
    private By popupContainer = By.xpath("//div[contains(@class,'popover') or contains(@class,'popup') or contains(@class,'store')][.//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]]");

    // STRICT LOCATOR POLICY for 'Select My Store' link
    private By selectMyStoreByXpathA = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreByXpathButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public String getPopupMessage() {
        WebElement popup = WaitUtils.waitForElementVisible(driver, driver.findElement(popupContainer));
        return BrowserUtils.getText(popup);
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement link = driver.findElement(selectMyStoreByXpathA);
            return BrowserUtils.isDisplayed(link);
        } catch (Exception e1) {
            try {
                WebElement btn = driver.findElement(selectMyStoreByXpathButton);
                return BrowserUtils.isDisplayed(btn);
            } catch (Exception e2) {
                return false;
            }
        }
    }
}
