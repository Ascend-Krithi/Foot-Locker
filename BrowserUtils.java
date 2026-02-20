package com.fl.automation.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BrowserUtils {
    public static void jsClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void typeAndEnter(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }

    public static void hover(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
