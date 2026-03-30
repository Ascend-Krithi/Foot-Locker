package com.fl.automation.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":

                ChromeOptions chromeOptions = new ChromeOptions();

                // ── 1. Read Chrome binary path from system property or env var ──
                String chromeBinary = System.getProperty("chromeBinary");
                if (chromeBinary == null || chromeBinary.isEmpty()) {
                    chromeBinary = System.getenv("CHROME_PATH");
                }
                if (chromeBinary != null && !chromeBinary.isEmpty()) {
                    System.out.println("Using Chrome binary: " + chromeBinary);
                    chromeOptions.setBinary(chromeBinary);
                }

                // ── 2. Read headless flag ──
                String headlessEnv = System.getProperty("headless");
                if (headlessEnv == null || headlessEnv.isEmpty()) {
                    headlessEnv = System.getenv("HEADLESS");
                }
                boolean headless = "true".equalsIgnoreCase(headlessEnv);
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                // ── 3. Required CI / Linux flags ──
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-setuid-sandbox");
                chromeOptions.addArguments("--ignore-certificate-errors");

                // ── 4. Anti-bot / stealth flags ──
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments(
                    "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/124.0.0.0 Safari/537.36"
                );
                chromeOptions.setExperimentalOption(
                    "excludeSwitches", Arrays.asList("enable-automation", "enable-logging")
                );
                chromeOptions.setExperimentalOption("useAutomationExtension", false);

                // ── 5. Let WebDriverManager match chromedriver to the binary ──
                if (chromeBinary != null && !chromeBinary.isEmpty()) {
                    WebDriverManager.chromedriver()
                        .browserPath(chromeBinary)
                        .setup();
                } else {
                    WebDriverManager.chromedriver().setup();
                }

                driver = new ChromeDriver(chromeOptions);

                // ── 6. Remove webdriver flag via JS (extra stealth) ──
                ((ChromeDriver) driver).executeCdpCommand(
                    "Page.addScriptToEvaluateOnNewDocument",
                    java.util.Map.of(
                        "source",
                        "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
                    )
                );

                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        return driver;
    }
}
