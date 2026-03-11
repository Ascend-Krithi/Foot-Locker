# Foot Locker Automation Framework

## Overview
Selenium TestNG automation framework for Foot Locker web application testing.

## Tech Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- Extent Reports 5.1.1
- Maven 3.x

## Project Structure
- `src/main/java/com/fl/automation/` - Framework core, pages, helpers, utils
- `src/test/java/com/fl/automation/` - Test classes, base test, listeners
- `src/test/resources/` - TestNG suite configuration

## Running Tests

### Local Execution
```bash
mvn clean test
```

### Headless Mode
```bash
mvn clean test -Dheadless=true
```

## Reports
- **Extent Report:** `test-output/ExtentReport.html`
- **TestNG Report:** `target/surefire-reports/index.html`
- **Screenshots:** `test-output/screenshots/`

## CI/CD
GitHub Actions workflow runs on push/PR to main/develop branches.
