package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[type='search']");
    private By searchInputName = By.cssSelector("input[name='q']");
    private By searchInputAria = By.cssSelector("input[aria-label*='Search']");
    private By searchInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearch(String searchTerm) {
        WebElement input;
        try {
            input = WaitUtils.waitForElementVisible(driver, searchInput);
        } catch (Exception e1) {
            try {
                input = WaitUtils.waitForElementVisible(driver, searchInputName);
            } catch (Exception e2) {
                try {
                    input = WaitUtils.waitForElementVisible(driver, searchInputAria);
                } catch (Exception e3) {
                    input = WaitUtils.waitForElementVisible(driver, searchInputPlaceholder);
                }
            }
        }
        input.clear();
        input.sendKeys(searchTerm);
        input.submit();
    }
}
