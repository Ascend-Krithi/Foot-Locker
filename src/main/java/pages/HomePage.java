package pages;

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserUtils;
import utils.WaitUtils;

public class HomePage {
    private WebDriver driver;

    // STRICT LOCATOR POLICY for 'Find a Store' button
    private By findStoreByLinkText = By.linkText("Find a Store");
    private By findStoreByCss = By.cssSelector("header a[href*=stores.footlocker.com]");
    private By findStoreByXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    private WebElement getFindAStoreButton() {
        try {
            return driver.findElement(findStoreByLinkText);
        } catch (Exception e1) {
            try {
                return driver.findElement(findStoreByCss);
            } catch (Exception e2) {
                return driver.findElement(findStoreByXpath);
            }
        }
    }

    public void clickFindAStore() {
        WebElement btn = getFindAStoreButton();
        WaitUtils.waitForElementClickable(driver, btn);
        BrowserUtils.click(btn);
    }
}
