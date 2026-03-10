# Foot Locker Automation Framework

Enterprise-grade Java Selenium TestNG automation framework for Foot Locker web application testing.

## Technology Stack

- **Java**: 17
- **Selenium WebDriver**: 4.21.0
- **TestNG**: 7.10.2
- **ExtentReports**: 5.1.1
- **Maven**: 3.x
- **WebDriverManager**: 5.9.2

## Project Structure

```
root
├── .github/workflows/
│   └── selenium-tests.yml
├── src/
│   ├── main/java/com/fl/automation/
│   │   ├── core/
│   │   │   └── DriverFactory.java
│   │   ├── helpers/
│   │   │   └── StoreLocatorHelper.java
│   │   ├── pages/
│   │   │   └── HomePage.java
│   │   └── utils/
│   │       ├── ExtentManager.java
│   │       └── ScreenshotUtil.java
│   └── test/java/com/fl/automation/
│       ├── core/
│       │   └── BaseTest.java
│       ├── listeners/
│       │   └── TestListener.java
│       └── tests/
│           ├── TS001_TC001_.java
│           ├── TS001_TC002_.java
│           └── TS001_TC003_.java
├── pom.xml
└── README.md
```

## Setup Instructions

### Prerequisites

1. **Java 17** installed
2. **Maven 3.x** installed
3. **Google Chrome** browser installed

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd footlocker-automation
   ```

2. Install dependencies:
   ```bash
   mvn clean install -DskipTests
   ```

## Running Tests

### Local Execution

```bash
# Run all tests
mvn clean test

# Run with custom browser (headless mode off)
mvn clean test -Dheadless=false

# Run specific test suite
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### CI/CD Execution

Tests automatically run on:
- Push to `main` branch
- Pull requests to `main`
- Manual workflow dispatch

View results in GitHub Actions artifacts:
- Test results: `test-results`
- Extent reports: `extent-reports`

## Test Reports

After test execution, reports are generated at:
- **ExtentReports HTML**: `test-output/ExtentReport.html`
- **TestNG Results**: `target/surefire-reports/`
- **Screenshots** (on failure): `test-output/screenshots/`

## Framework Features

### Strict Locator Policy
- All locators defined in Page Objects only
- Fallback locator strategy for robustness
- No locators in test classes

### Robust Wait Strategy
- WebDriverWait with explicit conditions
- No Thread.sleep usage
- JavaScript click fallbacks

### CI/CD Optimized
- Headless Chrome execution
- Selenium Manager for driver provisioning
- Linux-compatible flags (--no-sandbox, --disable-dev-shm-usage)

### Reporting
- ExtentReports integration
- Automatic screenshot capture on failure
- TestNG listener for real-time reporting

## Test Coverage

### Store Locator Tests (TS-001)

1. **TC-001**: Verify Find Store popup and Select My Store link visibility
2. **TC-002**: Verify Store Locator popup elements (Location textbox, Search button)
3. **TC-003**: Verify store search functionality with location input

## Contributing

1. Follow existing code structure
2. Adhere to strict locator policy
3. Use WebDriverWait for all element interactions
4. Add TestNG assertions for validations
5. Ensure tests pass locally before committing

## Support

For issues or questions, please contact the QA Automation team.
