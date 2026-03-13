# Foot Locker Automation Framework

## Overview
Complete Java Selenium TestNG automation framework for Foot Locker with 41 test cases covering:
- Store Locator functionality (SCRUM-17166)
- Eco Home Hub scenarios (SCRUM-19509)
- Marketplace scenarios (SCRUM-19509)

## Technology Stack
- Java 17
- Selenium WebDriver 4.21.0
- TestNG 7.10.2
- Extent Reports 5.1.1
- Lombok 1.18.32
- Maven 3.x

## Project Structure
```
footlocker-selenium-framework/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── fl/
│   │               └── automation/
│   │                   ├── core/
│   │                   │   └── DriverFactory.java
│   │                   ├── helpers/
│   │                   │   └── StoreLocatorHelper.java
│   │                   ├── pages/
│   │                   │   └── HomePage.java
│   │                   └── utils/
│   │                       ├── ExtentManager.java
│   │                       └── ScreenshotUtil.java
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── fl/
│       │           └── automation/
│       │               ├── core/
│       │               │   └── BaseTest.java
│       │               ├── listeners/
│       │               │   └── TestListener.java
│       │               └── tests/
│       │                   ├── TS001_TC001_SCRUM17166.java
│       │                   ├── TS001_TC002_SCRUM17166.java
│       │                   └── ... (41 test files total)
│       └── resources/
│           └── testng.xml
```

## Test Cases
### SCRUM-17166 (Store Locator - 3 tests)
- TC3193: Find a Store popup validation
- TC3194: Store locator popup functionality
- TC3195: Search stores in Boston

### SCRUM-19509 (Eco Home Hub - 20 tests)
- TC4104-TC4123: TS-001 to TS-008 scenarios

### SCRUM-19509 (Marketplace - 18 tests)
- TC4139-TC4156: TS-001 to TS-015 scenarios

## Setup Instructions
1. Prerequisites:
   - Java 17 or higher
   - Maven 3.6+
   - Chrome browser

2. Clone the repository:
   ```bash
   git clone <repository-url>
   cd footlocker-selenium-framework
   ```

3. Install dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests
### Run all tests:
```bash
mvn clean test
```

### Run specific test suite:
```bash
mvn clean test -Dtest=TS001_TC001_SCRUM17166
```

### Run with TestNG XML:
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

## CI/CD Configuration
The framework is configured for headless execution with:
- `--headless=new`
- `--no-sandbox`
- `--disable-dev-shm-usage`
- `--disable-gpu`

## Reports
- Extent Reports: `test-output/ExtentReport.html`
- TestNG Reports: `test-output/index.html`
- Screenshots: `test-output/screenshots/`

## Key Features
- Page Object Model (POM) design pattern
- Explicit waits (40-60s timeout)
- JavaScript click fallback mechanism
- Comprehensive logging with Extent Reports
- Screenshot capture on test failure
- CI/CD ready configuration
- Robust locator strategy with fallbacks

## Locator Strategy
All locators follow strict fallback order:
1. Primary locator (ID/LinkText)
2. CSS Selector fallback
3. XPath fallback

## Contact
For issues or questions, please contact the automation team.