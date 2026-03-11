package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    private By locationTextbox1 = By.cssSelector("input[placeholder*='Enter address' i], input[placeholder*='city' i], input[placeholder*='ZIP' i]");
    private By locationTextbox2 = By.cssSelector("input[aria-label*='Location' i], input[type='search']");
    private By locationTextbox3 = By.cssSelector("input[name*='location' i], input[id*='location' i]");
    private By locationTextbox4 = By.cssSelector("input[type='search']");
    private By locationTextbox5 = By.cssSelector("input[name='q']");
    private By locationTextbox6 = By.cssSelector("input[aria-label*='Search']");
    private By locationTextbox7 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");

    private By searchButton1 = By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]");
    private By searchButton2 = By.cssSelector("[aria-label*='Search for store' i], button[type='submit']");
    private By searchButton3 = By.cssSelector("button[aria-label*='Search' i]");
    private By searchButton4 = By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]");

    private By storeResults = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public boolean isLocationTextboxVisible() {
        try {
            WebElement element = null;
            try {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox1));
            } catch (Exception e1) {
                try {
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox2));
                } catch (Exception e2) {
                    try {
                        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox3));
                    } catch (Exception e3) {
                        try {
                            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox4));
                        } catch (Exception e4) {
                            try {
                                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox5));
                            } catch (Exception e5) {
                                try {
                                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox6));
                                } catch (Exception e6) {
                                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox7));
                                }
                            }
                        }
                    }
                }
            }
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonVisible() {
        try {
            WebElement element = null;
            try {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton1));
            } catch (Exception e1) {
                try {
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton2));
                } catch (Exception e2) {
                    try {
                        element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton3));
                    } catch (Exception e3) {
                        element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton4));
                    }
                }
            }
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox1));
        } catch (Exception e1) {
            try {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox2));
            } catch (Exception e2) {
                try {
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox3));
                } catch (Exception e3) {
                    try {
                        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox4));
                    } catch (Exception e4) {
                        try {
                            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox5));
                        } catch (Exception e5) {
                            try {
                                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox6));
                            } catch (Exception e6) {
                                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox7));
                            }
                        }
                    }
                }
            }
        }
        element.clear();
        element.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement element = null;
        try {
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(searchButton1));
                element.click();
                return;
            } catch (Exception e1) {
                try {
                    element = wait.until(ExpectedConditions.elementToBeClickable(searchButton2));
                    element.click();
                    return;
                } catch (Exception e2) {
                    try {
                        element = wait.until(ExpectedConditions.elementToBeClickable(searchButton3));
                        element.click();
                        return;
                    } catch (Exception e3) {
                        element = wait.until(ExpectedConditions.elementToBeClickable(searchButton4));
                        element.click();
                        return;
                    }
                }
            }
        } catch (Exception e) {
            try {
                element = driver.findElement(searchButton1);
            } catch (Exception ex1) {
                try {
                    element = driver.findElement(searchButton2);
                } catch (Exception ex2) {
                    try {
                        element = driver.findElement(searchButton3);
                    } catch (Exception ex3) {
                        element = driver.findElement(searchButton4);
                    }
                }
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storeResults)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}