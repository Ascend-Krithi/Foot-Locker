# Foot Locker Automation Framework

## Overview
Complete Selenium TestNG automation framework for Foot Locker with 47 test cases covering store locator functionality, Eco Home Hub features, and marketplace operations.

## Technology Stack
- Java 17
- Selenium WebDriver 4.21.0
- TestNG 7.10.2
- ExtentReports 5.1.1
- WebDriverManager 5.9.2
- Maven 3.x

## Project Structure
```
footlocker-automation/
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
│       │                   ├── TS001_TC001.java
│       │                   ├── TS001_TC002.java
│       │                   └── ... (47 test classes total)
│       └── resources/
│           └── testng.xml
```

## Test Coverage
### Foot Locker Store Locator (10 tests)
- TC3193-TC3195: Basic store locator functionality
- TC4170-TC4175: Advanced store locator features

### Eco Home Hub (24 tests)
- TC4104-TC4123: Customer registration, loan applications, installer management

### Marketplace (13 tests)
- TC4139-TC4156: Search, project details, user management, reviews

## Setup Instructions
1. **Prerequisites**
   - Java 17 or higher
   - Maven 3.6+
   - Chrome browser

2. **Clone and Build**
   ```bash
   git clone <repository-url>
   cd footlocker-automation
   mvn clean install
   ```

3. **Run Tests**
   ```bash
   # Run all tests
   mvn clean test
   
   # Run specific test suite
   mvn clean test -Dtest=TS001_TC001
   ```

## Configuration
- **Browser**: Chrome (headless mode enabled by default)
- **Timeouts**: 40-60 seconds for explicit waits
- **Reports**: ExtentReports generated in `test-output/` directory

## Coding Standards
- All locators defined in Page Objects or Helper classes
- No Thread.sleep() - only WebDriverWait
- JavaScript click fallback for StaleElementReferenceException
- TestNG assertions only
- All tests extend BaseTest

## Reporting
- ExtentReports HTML reports generated after each test run
- Screenshots captured on test failure
- Detailed logs with test steps

## CI/CD Integration
Framework is CI/CD ready with:
- Maven Surefire plugin configured
- Headless Chrome execution
- XML test results for Jenkins/GitLab CI

## Contact
For issues or questions, please contact the QA Automation team.