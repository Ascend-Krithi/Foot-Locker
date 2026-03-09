package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class HomePage {
    private WebDriver driver;
    private By findAStoreBtn = By.id("find-store-link");
    private By findAStorePopup = By.id("find-store-popup");
    private By selectMyStoreLink = By.xpath("//a[text()='Select My Store']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        driver.findElement(findAStoreBtn).click();
    }

    public boolean isFindAStorePopupDisplayed() {
        return driver.findElement(findAStorePopup).isDisplayed();
    }

    public void clickSelectMyStore() {
        driver.findElement(selectMyStoreLink).click();
    }

    public boolean isSelectMyStoreLinkPresent() {
        return driver.findElements(selectMyStoreLink).size() > 0;
    }
}
