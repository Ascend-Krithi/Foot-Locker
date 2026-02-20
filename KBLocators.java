package com.fl.automation.pages;

import org.openqa.selenium.By;

public class KBLocators {
    // 1. Header – Find a Store
    public static final By[] FIND_A_STORE = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    // 2. Select / Set My Store
    public static final By[] SELECT_MY_STORE = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]") ,
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    // 3. Search Input
    public static final By[] SEARCH_INPUT = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    // 4. Store Result Cards
    public static final By[] STORE_RESULT_CARDS = new By[] {
        By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']")
    };

    // 5. Store Address Inside Card
    public static final By[] STORE_ADDRESS = new By[] {
        By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']")
    };

    // 6. Set My Store Button Inside Card
    public static final By[] SET_MY_STORE_BUTTON = new By[] {
        By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]")
    };

    // 7. Empty Results Message
    public static final By[] EMPTY_RESULTS_MESSAGE = new By[] {
        By.xpath("//*[contains(.,'There are no locations in your search area')]")
    };
}
