# Foot Locker Automation Framework

## Overview
Enterprise-grade Selenium TestNG automation framework for Foot Locker web application testing.

## Tech Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- Maven 3.9+
- Extent Reports 5.1.1
- WebDriverManager 5.9.2
- Lombok 1.18.32

## Project Structure
```
src/
├── main/java/com/fl/automation/
│   ├── core/          # DriverFactory
│   ├── helpers/       # Business logic helpers (StoreLocatorHelper)
│   ├── pages/         # Page Object Model (HomePage)
│   └── utils/         # Reporting & screenshots (ExtentManager, ScreenshotUtil)
└── test/java/com/fl/automation/
    ├── core/          # BaseTest
    ├── listeners/     # TestNG listeners (TestListener)
    └── tests/         # Test classes (7 test scenarios)
```

## Test Scenarios
1. **TS001_TC001** - Launch homepage, verify Find a Store functionality
2. **TS001_TC002** - Verify Store Locator popup elements
3. **TS001_TC003** - Search for stores in Boston, MA
4. **TS001_TC004** - Verify specific Boston store address
5. **TS001_TC005** - Set Boston store as preferred
6. **TS001_TC006** - Verify store confirmation indicators
7. **TS001_TC007** - Verify store persistence across navigation

## Running Tests

### Local Execution
```bash
mvn clean test
```

### CI/CD Pipeline
Tests run automatically on push/PR to main/develop branches via GitHub Actions.

## Reports
- **Extent Reports**: `test-output/ExtentReport.html`
- **Screenshots**: `test-output/screenshots/`

## Framework Standards
- All locators defined in Page/Helper classes only
- WebDriverWait (40-60s) - NO Thread.sleep in production code
- TestNG assertions exclusively
- All tests extend BaseTest
- Locator fallback strategy implemented
- JS click fallback for stubborn elements
- Cookie consent handling
- CI-safe Chrome options

## Key Features
- **Robust Locator Strategy**: Multiple fallback locators for each element
- **Explicit Waits**: WebDriverWait with configurable timeouts
- **Extent Reporting**: Comprehensive HTML reports with screenshots
- **Screenshot on Failure**: Automatic screenshot capture for failed tests
- **Page Object Model**: Clean separation of test logic and page interactions
- **CI/CD Ready**: GitHub Actions workflow included

## Dependencies
All dependencies are managed via Maven (see pom.xml):
- Selenium Java 4.21.0
- WebDriverManager 5.9.2
- TestNG 7.10.2
- Extent Reports 5.1.1
- Lombok 1.18.32
- Commons IO 2.11.0

## Author
Foot Locker QA Automation Team

## License
Proprietary - Foot Locker Inc.