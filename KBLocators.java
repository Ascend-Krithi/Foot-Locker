package com.fl.automation.pages;

import org.openqa.selenium.By;

public class KBLocators {
    // Header – Find a Store
    public static final By[] FIND_A_STORE = {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    // Select / Set My Store
    public static final By[] SELECT_MY_STORE = {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    // Search Input
    public static final By[] SEARCH_INPUT = {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    // Store Result Cards
    public static final By[] STORE_RESULT_CARDS = {
        By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']")
    };

    // Store Address Inside Card
    public static final By[] STORE_ADDRESS = {
        By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']")
    };

    // Set My Store Button Inside Card
    public static final By[] SET_MY_STORE_BUTTON = {
        By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]")
    };

    // Empty Results Message
    public static final By[] EMPTY_RESULTS_MESSAGE = {
        By.xpath("//*[contains(.,'There are no locations in your search area')]")
    };
}