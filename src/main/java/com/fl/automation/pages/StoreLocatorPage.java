package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private By searchInput1 = By.cssSelector("input[type=search]");
    private By searchInput2 = By.cssSelector("input[name=q]");
    private By searchInput3 = By.cssSelector("input[aria-label*=Search]");
    private By searchInput4 = By.cssSelector("input[placeholder*=Search i], input[placeholder*=City i], input[placeholder*=ZIP i]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearch(String searchText) {
        WebElement input = null;
        if (driver.findElements(searchInput1).size() > 0) {
            input = driver.findElement(searchInput1);
        } else if (driver.findElements(searchInput2).size() > 0) {
            input = driver.findElement(searchInput2);
        } else if (driver.findElements(searchInput3).size() > 0) {
            input = driver.findElement(searchInput3);
        } else if (driver.findElements(searchInput4).size() > 0) {
            input = driver.findElement(searchInput4);
        }
        if (input != null) {
            BrowserUtils.type(driver, input, searchText);
            input.submit();
        } else {
            throw new RuntimeException("Search input not found");
        }
    }
}