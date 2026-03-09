# Foot Locker Automation Framework

## Overview
This is a Maven-based Selenium TestNG automation framework for Foot Locker web application testing using Page Object Model architecture.

## Prerequisites
- Java 17
- Maven 3.6+
- Chrome Browser

## Project Structure
```
root
├─ .github/workflows/
│  └─ selenium-tests.yml
├─ src/
│  ├─ main/java/com/fl/automation/
│  │  ├─ core/
│  │  ├─ helpers/
│  │  ├─ pages/
│  │  └─ utils/
│  └─ test/java/com/fl/automation/
│     ├─ core/
│     ├─ listeners/
│     └─ tests/
├─ pom.xml
└─ README.md
```

## Running Tests

### Local Execution
```bash
mvn clean test
```

### CI/CD Execution
Tests run automatically on push/PR to main branch via GitHub Actions.

## Test Reports
- ExtentReports: `test-output/ExtentReport.html`
- TestNG Reports: `target/surefire-reports/`
- Screenshots: `test-output/screenshots/`

## Framework Features
- Page Object Model
- TestNG for test execution
- ExtentReports for reporting
- WebDriverManager for driver management
- Robust locator strategies
- Screenshot capture on failure
- CI/CD integration with GitHub Actions

## Test Cases
This framework contains 27 automated test cases covering:
- Store Locator functionality
- Store search and selection
- Store persistence across navigation
- Validation of store information

## Configuration
- Base URL: https://www.footlocker.com/
- Browser: Chrome (headless in CI)
- Timeouts: 60 seconds for waits

## Contact
For issues or questions, please contact the QA team.