# Foot Locker Selenium TestNG Automation Framework

## Overview
Complete Java Selenium TestNG automation framework for Foot Locker with 41 test cases covering:
- Store Locator functionality (TC3193-TC3195)
- Eco Home Hub features (TC4104-TC4123)
- Marketplace features (TC4139-TC4156)

## Prerequisites
- Java 17+
- Maven 3.6+
- Chrome browser

## Setup
```bash
mvn clean install
```

## Run Tests
```bash
mvn clean test
```

## Framework Structure
- `src/main/java/com/fl/automation/core/` - Core framework components
- `src/main/java/com/fl/automation/pages/` - Page Object classes
- `src/main/java/com/fl/automation/helpers/` - Helper utilities
- `src/main/java/com/fl/automation/utils/` - Utility classes
- `src/test/java/com/fl/automation/tests/` - Test classes
- `src/test/java/com/fl/automation/listeners/` - TestNG listeners

## Reports
Extent Reports generated in `test-output/ExtentReport.html`