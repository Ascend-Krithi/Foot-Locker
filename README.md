# Footlocker Store Locator Automation Framework

## Overview
Java Selenium TestNG framework for automating Footlocker store locator functionality.

## Test Coverage
- TC 4200 (TS001_TC001): Verify Find a Store popup displays
- TC 4201 (TS001_TC002): Verify popup contains Location textbox and Search button
- TC 4202 (TS001_TC003): Search for stores in Boston MA
- TC 4203 (TS001_TC004): Verify specific store address 375 Washington Street
- TC 4204 (TS001_TC005): Set 375 Washington Street as preferred store
- TC 4205 (TS001_TC006): Verify confirmation after setting preferred store
- TC 4206 (TS001_TC007): Verify preferred store persists across pages

## Technology Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- ExtentReports 5.1.1
- WebDriverManager 5.9.2
- Lombok 1.18.32

## Prerequisites
- JDK 17 or higher
- Maven 3.6+

## Execution
```bash
mvn clean test
```

## Reports
ExtentReports HTML reports generated in `test-output/ExtentReport.html`

## CI/CD Ready
Configured with headless Chrome options for CI environments.