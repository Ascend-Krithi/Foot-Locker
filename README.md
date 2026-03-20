# Foot Locker Automation Framework

## Overview
This is a Selenium TestNG automation framework for Foot Locker web application testing.

## Prerequisites
- Java 17
- Maven 3.6+

## Running Tests
```bash
mvn clean test
```

## Framework Structure
- **src/main/java/com/fl/automation/core**: Core framework components (DriverFactory)
- **src/main/java/com/fl/automation/pages**: Page Object classes
- **src/main/java/com/fl/automation/helpers**: Helper classes for complex workflows
- **src/main/java/com/fl/automation/utils**: Utility classes (ExtentManager, ScreenshotUtil)
- **src/test/java/com/fl/automation/core**: Base test class
- **src/test/java/com/fl/automation/tests**: Test classes
- **src/test/java/com/fl/automation/listeners**: TestNG listeners
- **src/test/resources**: TestNG configuration

## Reporting
Extent Reports are generated in `test-output/ExtentReport.html` after test execution.