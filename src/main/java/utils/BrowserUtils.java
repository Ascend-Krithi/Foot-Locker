package utils;

import org.openqa.selenium.WebDriver;

public class BrowserUtils {
    public static void navigateToBaseUrl(WebDriver driver) {
        String url = ConfigReader.get("baseUrl");
        driver.get(url);
    }
}
