package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeAddress = By.className("store-address");
    private By setMyStoreBtn = By.xpath("//button[contains(text(),'Set My Store')]");
    private By confirmationIndicator = By.id("store-confirmation-indicator");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getStoreAddresses() {
        return driver.findElements(storeAddress);
    }

    public boolean isStorePresent(String address) {
        for (WebElement el : getStoreAddresses()) {
            if (el.getText().trim().equals(address)) {
                return true;
            }
        }
        return false;
    }

    public void clickSetMyStore(String address) {
        for (WebElement el : getStoreAddresses()) {
            if (el.getText().trim().equals(address)) {
                WebElement btn = el.findElement(By.xpath("../following-sibling::button[contains(text(),'Set My Store')]"));
                btn.click();
                break;
            }
        }
    }

    public boolean isConfirmationIndicatorDisplayed() {
        return driver.findElements(confirmationIndicator).size() > 0 && driver.findElement(confirmationIndicator).isDisplayed();
    }
}
