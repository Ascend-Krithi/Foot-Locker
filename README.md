# Foot Locker Selenium TestNG Automation Framework

## Overview
This project is a Selenium TestNG automation framework for Foot Locker's store locator functionality, covering 7 TestRail test cases.

## Tech Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- ExtentReports 5.1.1
- WebDriverManager 5.9.2
- Lombok 1.18.32

## Structure
- All locators are in `HomePage.java` with strict fallback order.
- Tests are in `src/test/java/com/fl/automation/tests/` and extend `BaseTest`.
- CI/CD ready with GitHub Actions workflow.

## Running Tests
```
mvn clean test
```

## CI/CD
See `.github/workflows/selenium-tests.yml` for GitHub Actions pipeline.
