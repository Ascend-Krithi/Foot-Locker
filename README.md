# Foot Locker Automation Framework

## Overview
Selenium TestNG automation framework for Foot Locker web application testing.

## Tech Stack
- Java 17
- Selenium 4.21+
- TestNG 7.10+
- Maven 3.9+
- Extent Reports 5.1.1

## Project Structure
```
src/
├── main/java/com/fl/automation/
│   ├── core/          # Driver factory
│   ├── helpers/       # Business logic helpers
│   ├── pages/         # Page Object Model
│   └── utils/         # Utilities (reporting, screenshots)
└── test/java/com/fl/automation/
    ├── core/          # BaseTest
    ├── listeners/     # TestNG listeners
    └── tests/         # Test classes
```

## Running Tests

### Local Execution
```bash
mvn clean test
```

### CI/CD
Tests run automatically on push/PR via GitHub Actions.

## Reports
- Extent Reports: `test-output/ExtentReport.html`
- Screenshots: `test-output/screenshots/`

## Standards
- All locators in Page/Helper classes only
- WebDriverWait (40-60s), no Thread.sleep
- TestNG assertions
- CI-safe Chrome options