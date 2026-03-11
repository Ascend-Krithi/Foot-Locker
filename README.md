# Foot Locker Automation Framework

## Overview
This is a Selenium-based test automation framework for Foot Locker web application testing.

## Tech Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- Maven 3.x
- ExtentReports 5.1.1
- WebDriverManager 5.9.2

## Project Structure
```
footlocker-automation/
├─ src/main/java/com/fl/automation/
│  ├─ core/          # Driver factory
│  ├─ helpers/       # Helper classes
│  ├─ pages/         # Page Object Model
│  └─ utils/         # Utilities (reporting, screenshots)
├─ src/test/java/com/fl/automation/
│  ├─ core/          # BaseTest
│  ├─ listeners/     # TestNG listeners
│  └─ tests/         # Test classes
└─ src/test/resources/
   └─ testng.xml     # TestNG suite configuration
```

## Running Tests

### Locally
```bash
mvn clean test
```

### CI/CD
Tests run automatically on push/PR to `main` or `develop` branches via GitHub Actions.

## Reports
- ExtentReports: `test-output/ExtentReport.html`
- Screenshots (on failure): `test-output/screenshots/`

## Coding Standards
- Use Page Object Model
- Implement locator fallback strategy
- Use explicit waits (WebDriverWait)
- Extend BaseTest for all test classes
- Use TestNG assertions