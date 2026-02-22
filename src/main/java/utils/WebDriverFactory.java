package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
    public static WebDriver getDriver() {
        String browser = ConfigReader.get("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        throw new RuntimeException("Unsupported browser: " + browser);
    }
}
