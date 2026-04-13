package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeviceDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By colourSelector = By.xpath("//div[contains(@class,'colour') or contains(@class,'color')]//button | //select[@id='colour']");
    private By storageSelector = By.xpath("//div[contains(@class,'storage')]//button | //select[@id='storage']");
    private By paymentSelector = By.xpath("//div[contains(@class,'payment')]//button | //select[@id='payment']");
    private By nextButton = By.xpath("//button[contains(text(),'Next')] | //button[@id='next-button']");
    private By loginPopup = By.xpath("//div[contains(@class,'login-popup') or contains(@class,'auth-modal')]");
    private By loginPopupMessage = By.xpath("//*[contains(text(),'Please log in') or contains(text(),'create an account')]");
    private By loginWithHubIdButton = By.xpath("//button[contains(text(),'Log in with Hub ID')] | //a[contains(text(),'Log in with Hub ID')]");
    private By signUpLink = By.xpath("//a[contains(text(),'Sign up')] | //a[contains(text(),'create an account')]");

    public DeviceDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDeviceDetailsPageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(colourSelector));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSelectedColour() {
        try {
            WebElement colour = wait.until(ExpectedConditions.presenceOfElementLocated(colourSelector));
            return colour.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getSelectedStorage() {
        try {
            WebElement storage = wait.until(ExpectedConditions.presenceOfElementLocated(storageSelector));
            return storage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getSelectedPayment() {
        try {
            WebElement payment = wait.until(ExpectedConditions.presenceOfElementLocated(paymentSelector));
            return payment.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean areSelectorsVisible() {
        try {
            return driver.findElement(colourSelector).isDisplayed() &&
                   driver.findElement(storageSelector).isDisplayed() &&
                   driver.findElement(paymentSelector).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areSelectorsEnabled() {
        try {
            return driver.findElement(colourSelector).isEnabled() &&
                   driver.findElement(storageSelector).isEnabled() &&
                   driver.findElement(paymentSelector).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectColour(String colour) {
        try {
            WebElement colourElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'" + colour + "')] | //option[contains(text(),'" + colour + "')]"))
            );
            colourElement.click();
        } catch (Exception e) {
            System.out.println("Colour option not found: " + colour);
        }
    }

    public void selectStorage(String storage) {
        try {
            WebElement storageElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'" + storage + "')] | //option[contains(text(),'" + storage + "')]"))
            );
            storageElement.click();
        } catch (Exception e) {
            System.out.println("Storage option not found: " + storage);
        }
    }

    public void selectPayment(String payment) {
        try {
            WebElement paymentElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'" + payment + "')] | //option[contains(text(),'" + payment + "')]"))
            );
            paymentElement.click();
        } catch (Exception e) {
            System.out.println("Payment option not found: " + payment);
        }
    }

    public void clickNextButton() {
        try {
            WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            try {
                next.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
            }
        } catch (Exception e) {
            System.out.println("Next button not clickable");
        }
    }

    public boolean isNextButtonEnabled() {
        try {
            WebElement next = driver.findElement(nextButton);
            return next.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPopupDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(loginPopup));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPopupMessageDisplayed() {
        try {
            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(loginPopupMessage));
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginWithHubIdButtonVisible() {
        try {
            return driver.findElement(loginWithHubIdButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSignUpLinkVisible() {
        try {
            return driver.findElement(signUpLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLoginWithHubId() {
        try {
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginWithHubIdButton));
            loginBtn.click();
        } catch (Exception e) {
            System.out.println("Login button not found");
        }
    }

    public void clickSignUpLink() {
        try {
            WebElement signUp = wait.until(ExpectedConditions.elementToBeClickable(signUpLink));
            signUp.click();
        } catch (Exception e) {
            System.out.println("Sign up link not found");
        }
    }

    public boolean isUserNavigatedToNextStep() {
        try {
            Thread.sleep(2000);
            return !driver.getCurrentUrl().contains("device-details");
        } catch (Exception e) {
            return false;
        }
    }
}