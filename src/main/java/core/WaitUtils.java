package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private static final int TIMEOUT = 5;

    public static WebElement waitForVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static List<WebElement> waitForAllVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static boolean waitForInvisibility(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
