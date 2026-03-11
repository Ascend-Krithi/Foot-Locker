# Foot Locker Automation Framework

## Overview
This is a Selenium-based test automation framework for Foot Locker web application testing.

## Tech Stack
- **Java 17**
- **Selenium 4.21.0**
- **TestNG 7.10.2**
- **ExtentReports 5.1.1**
- **Maven 3.x**

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

### Locally
```bash
mvn clean test
```

### CI/CD
Tests run automatically on push/PR to `main` or `develop` branches via GitHub Actions.

## Reports
- **ExtentReports**: `test-output/ExtentReport.html`
- **Screenshots**: `test-output/screenshots/`

## Notes
- Uses Selenium Manager (no WebDriverManager needed in CI)
- Chrome runs in headless mode in CI
- All tests extend `BaseTest` for driver lifecycle management
