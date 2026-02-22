package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;

    private By popupContainer = By.xpath("//div[contains(@class,'popover') or contains(@class,'popup') or contains(@class,'store')][.//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPopupMessage() {
        WebElement popup = driver.findElement(popupContainer);
        return popup.getText();
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement link = driver.findElement(By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]") );
            return link.isDisplayed();
        } catch (NoSuchElementException e1) {
            try {
                WebElement button = driver.findElement(By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]") );
                return button.isDisplayed();
            } catch (NoSuchElementException e2) {
                return false;
            }
        }
    }
}
