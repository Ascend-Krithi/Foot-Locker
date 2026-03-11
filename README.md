# Foot Locker Automation Framework

## Overview
This is a Selenium-based automation framework for testing the Foot Locker web application.

## Tech Stack
- Java 17
- Selenium 4.21+
- TestNG 7.10+
- WebDriverManager 5.9+
- Extent Reports 5.1.1
- Maven 3.x

## Project Structure
- `src/main/java/com/fl/automation/core/` - Core framework components
- `src/main/java/com/fl/automation/pages/` - Page Object Model classes
- `src/main/java/com/fl/automation/helpers/` - Helper classes
- `src/main/java/com/fl/automation/utils/` - Utility classes
- `src/test/java/com/fl/automation/tests/` - Test classes
- `src/test/resources/` - TestNG configuration

## Running Tests

### Local Execution
```bash
mvn clean test
```

### CI Execution
Tests run automatically on push/PR to main/develop branches via GitHub Actions.

## Reports
- Extent Reports: `test-output/ExtentReport.html`
- Screenshots: `test-output/screenshots/`
- Surefire Reports: `target/surefire-reports/`

## Coding Standards
- All tests extend `BaseTest`
- Use explicit waits (40-60s)
- JS click fallback for flaky elements
- TestNG assertions only
- Page Object Model pattern
- No Thread.sleep()