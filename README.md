# Foot Locker Automation Framework

Enterprise-grade Selenium TestNG automation framework for Foot Locker web application.

## Tech Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- WebDriverManager 5.9.2
- ExtentReports 5.1.1
- Maven 3.x

## Project Structure
```
src/
├── main/java/com/fl/automation/
│   ├── core/          # DriverFactory
│   ├── helpers/       # StoreLocatorHelper
│   ├── pages/         # Page Object Models
│   └── utils/         # ExtentManager, ScreenshotUtil
└── test/java/com/fl/automation/
    ├── core/          # BaseTest
    ├── listeners/     # TestListener
    └── tests/         # Test classes
```

## Running Tests

### Local Execution
```bash
mvn clean test
```

### CI Execution
Tests run automatically on push/PR to main branch via GitHub Actions.

## Reports
- ExtentReports: `test-output/ExtentReport.html`
- TestNG Reports: `target/surefire-reports/`
- Screenshots: `test-output/screenshots/`

## Coding Standards
- All locators in Page/Helper classes only
- WebDriverWait (40-60s) - no Thread.sleep
- JS click fallback for stale elements
- TestNG assertions only
- Selenium Manager for driver provisioning in CI
