# Footlocker Selenium TestNG Automation Framework

## Overview
Complete Java Selenium TestNG framework for Footlocker store locator automation testing.

## Test Cases Covered
- **TC 3193 (TS-001 TC-001)**: Verify Find a Store link and popup with Select My Store link
- **TC 3194 (TS-001 TC-002)**: Verify store locator popup elements (Location textbox and Search button)
- **TC 3195 (TS-001 TC-003)**: Verify store search functionality with Boston, MA location

## Technology Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- ExtentReports 5.1.1
- Lombok 1.18.32
- Maven 3.9+

## Framework Features
- Page Object Model (POM) design pattern
- Helper classes for reusable actions
- ExtentReports for detailed test reporting
- Screenshot capture on test failure
- Headless Chrome execution for CI/CD
- Selenium Manager (no WebDriverManager needed)
- Explicit waits (40-60s timeout)
- JavaScript click fallback mechanism
- TestNG listeners for enhanced reporting

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
│       │                   └── TS001_TC003_SCRUM17166.java
│       └── resources/
│           └── testng.xml
└── .github/
    └── workflows/
        └── selenium-tests.yml
```

## Prerequisites
- Java 17 or higher
- Maven 3.9 or higher
- Chrome browser (for local execution)

## Installation
1. Clone the repository
2. Navigate to project directory
3. Run: `mvn clean install`

## Execution

### Local Execution
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### CI/CD Execution
Tests automatically run on push/pull request via GitHub Actions workflow.

## Reports
- ExtentReports: `test-output/ExtentReport.html`
- TestNG Reports: `test-output/index.html`
- Screenshots: `test-output/screenshots/`

## Configuration
- Browser: Chrome (headless in CI)
- Base URL: https://www.footlocker.com
- Implicit Wait: None (using explicit waits only)
- Explicit Wait Timeout: 40-60 seconds
- CI Flags: --no-sandbox, --disable-dev-shm-usage, --headless=new

## Locator Strategy
All locators are defined in Page/Helper classes with fallback mechanisms:
1. Primary locator (most reliable)
2. Secondary locator (CSS/XPath alternative)
3. Tertiary locator (generic fallback)

## Best Practices Implemented
- No hardcoded waits (Thread.sleep)
- Selenium Manager for driver management
- JavaScript executor for stubborn elements
- Comprehensive error handling
- Detailed logging and reporting
- Screenshot on failure
- Reusable helper methods
- Clean separation of concerns

## Troubleshooting
- If tests fail due to element not found, check locators in HomePage.java and StoreLocatorHelper.java
- For timeout issues, adjust wait times in BaseTest.java
- For CI failures, verify Chrome flags in DriverFactory.java

## Author
Automation Engineering Team

## License
Proprietary - Footlocker Internal Use Only