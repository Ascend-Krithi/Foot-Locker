package pages;

import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void openHomePage() {
        driver.get("https://www.footlocker.com");
    }

    public void clickFindAStore() {
        try {
            driver.findElement(findStoreHeader).click();
        } catch (Exception e1) {
            try {
                driver.findElement(findStoreHeaderCss).click();
            } catch (Exception e2) {
                driver.findElement(findStoreHeaderXpath).click();
            }
        }
    }
}
