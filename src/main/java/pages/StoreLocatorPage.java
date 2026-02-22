package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;

    private By popupByXpath = By.xpath("//div[contains(@class,'popover') or contains(@class,'popup') or contains(@class,'store')][.//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]]");
    private By selectMyStoreLinkByXpath = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButtonByXpath = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPopup() {
        return driver.findElement(popupByXpath);
    }

    public WebElement getSelectMyStoreLink() {
        return driver.findElement(selectMyStoreLinkByXpath);
    }

    public WebElement getSelectMyStoreButton() {
        return driver.findElement(selectMyStoreButtonByXpath);
    }

    public void selectMyStore() {
        if (driver.findElements(selectMyStoreLinkByXpath).size() > 0) {
            driver.findElement(selectMyStoreLinkByXpath).click();
        } else {
            driver.findElement(selectMyStoreButtonByXpath).click();
        }
    }
}
