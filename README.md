# Foot Locker Automation Framework

## Overview
This is a Selenium-based automation framework for testing the Foot Locker web application using TestNG, Page Object Model, and Extent Reports.

## Prerequisites
- Java 17+
- Maven 3.8+
- Chrome Browser

## Project Structure
- `src/main/java/com/fl/automation/core` - Core framework components
- `src/main/java/com/fl/automation/pages` - Page Object classes
- `src/main/java/com/fl/automation/helpers` - Helper classes
- `src/main/java/com/fl/automation/utils` - Utility classes
- `src/test/java/com/fl/automation/tests` - Test classes
- `src/test/resources` - TestNG configuration

## Running Tests

### Local Execution
```bash
mvn clean test
```

### CI/CD
Tests run automatically on push/PR to main/develop branches via GitHub Actions.

## Reports
- Extent Reports: `test-output/ExtentReport.html`
- TestNG Reports: `target/surefire-reports/`
- Screenshots: `test-output/screenshots/`

## Key Features
- Selenium 4.21+ with WebDriverManager
- TestNG 7.10+ for test orchestration
- Extent Reports 5.1.1 for rich HTML reporting
- Page Object Model design pattern
- CI-safe headless Chrome execution
- Automatic screenshot capture on failure

## Test Cases
- TS001_TC001_: Verify Find Store popup and Select My Store link
- TS001_TC002_: Verify Store Locator elements (Location textbox and Search button)
- TS001_TC003_: Verify Store Search functionality with Boston, MA