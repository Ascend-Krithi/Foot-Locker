package pages;

import core.BrowserUtils;
import core.WaitUtils;
import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;
    private By selectMyStoreXpath = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store')]");
    private By selectMyStoreBtnXpath = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store')]");
    private By searchInput = By.cssSelector("input[type='search']");
    private By searchInputName = By.cssSelector("input[name='q']");
    private By searchInputAria = By.cssSelector("input[aria-label*='Search']");
    private By searchButton = By.cssSelector("button[type='submit']");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickSelectMyStore() {
        WebElement el;
        if (BrowserUtils.isDisplayed(driver.findElement(selectMyStoreXpath))) {
            el = driver.findElement(selectMyStoreXpath);
        } else {
            el = driver.findElement(selectMyStoreBtnXpath);
        }
        WaitUtils.waitForElementToBeClickable(driver, el);
        BrowserUtils.click(el);
    }

    public void enterLocation(String location) {
        WebElement input;
        if (BrowserUtils.isDisplayed(driver.findElement(searchInput))) {
            input = driver.findElement(searchInput);
        } else if (BrowserUtils.isDisplayed(driver.findElement(searchInputName))) {
            input = driver.findElement(searchInputName);
        } else {
            input = driver.findElement(searchInputAria);
        }
        WaitUtils.waitForElementToBeVisible(driver, input);
        BrowserUtils.type(input, location);
    }

    public void clickSearch() {
        WebElement btn = driver.findElement(searchButton);
        WaitUtils.waitForElementToBeClickable(driver, btn);
        BrowserUtils.click(btn);
    }
}
