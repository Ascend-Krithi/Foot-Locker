# Foot Locker Automation Framework

## Overview
Complete Java Selenium TestNG framework for Foot Locker automation with 47 test cases covering Store Locator, Eco Home Hub, and Marketplace functionality.

## Technology Stack
- **Java**: 17
- **Selenium WebDriver**: 4.21.0
- **TestNG**: 7.10.2
- **ExtentReports**: 5.1.1
- **WebDriverManager**: 5.9.2
- **Lombok**: 1.18.32
- **Maven**: 3.8+

## Test Coverage

### SCRUM-17166: Store Locator (6 tests)
- **TS001_TC001**: Find Store popup validation
- **TS001_TC002**: Store Locator textbox validation
- **TS001_TC003**: Store search Boston MA
- **TS001_TC004**: Verify store address
- **TS001_TC005**: Set preferred store
- **TS001_TC006**: Confirmation and persistence

### SCRUM-19509: Eco Home Hub (20 tests)
- **TS002_TC001 - TS002_TC020**: Customer registration, loan application, installer search, admin vetting, installer portal, security, deployment, analytics, validation

### SCRUM-19509: Marketplace (21 tests)
- **TS003_TC001 - TS003_TC021**: Home, search, project details, contact, login, registration, dashboard, apply, profile, logout, review, certifications, share, favorites, help

## Framework Structure
```
footlocker-selenium-testng/
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
│       │                   └── ... (47 test classes)
│       └── resources/
│           └── testng.xml
```

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.8 or higher
- Chrome browser installed

### Installation
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd footlocker-selenium-testng
   ```

2. Install dependencies:
   ```bash
   mvn clean install -DskipTests
   ```

### Running Tests

#### Run all tests:
```bash
mvn clean test
```

#### Run specific test suite:
```bash
mvn clean test -Dtest=TS001_TC001
```

#### Run in headless mode (CI/CD):
```bash
mvn clean test -Dheadless=true
```

## Configuration

### Browser Configuration
The framework uses Chrome by default with CI-safe options:
- Headless mode support
- No sandbox mode
- Disable dev shm usage
- Disable GPU

### Wait Strategy
- Explicit waits: 40-60 seconds
- No Thread.sleep() usage
- JavaScript click fallback for stale elements

## Reporting

### ExtentReports
Test execution reports are generated in:
```
target/extent-reports/ExtentReport.html
```

### Screenshots
Failure screenshots are captured automatically in:
```
target/screenshots/
```

## Coding Standards

1. **Locators**: All locators defined ONLY in Page/Helper classes
2. **Waits**: WebDriverWait with 40-60s timeout (NO Thread.sleep)
3. **Click Strategy**: JavaScript click fallback in try-catch blocks
4. **Assertions**: TestNG Assert for all validations
5. **Inheritance**: All tests extend BaseTest
6. **CI/CD**: Chrome options configured for headless execution

## Locator Strategy

The framework implements a fallback locator strategy for reliability:

### Cookie Accept Button
1. By.id("onetrust-accept-btn-handler")
2. By.cssSelector("button#onetrust-accept-btn-handler")
3. By.cssSelector("button[aria-label*='Accept' i]")
4. By.xpath("//button[contains(normalize-space(.),'Accept All Cookies')]")

### Find a Store Link
1. By.linkText("Find a Store")
2. By.cssSelector("header a[href*='stores.footlocker.com']")
3. By.xpath("//header//a[contains(.,'Find a Store')]")
4. By.xpath("//*[normalize-space()='Find a Store']")

### Store Locator Input
1. By.cssSelector("input[placeholder*='Enter address' i]")
2. By.cssSelector("input[aria-label*='Location' i]")
3. By.cssSelector("input[name*='location' i]")
4. By.xpath("//input[@placeholder='Enter address, city or post code']")

## CI/CD Integration

The framework is designed for seamless CI/CD integration:
- Headless Chrome execution
- No GUI dependencies
- Automatic report generation
- Screenshot capture on failure
- Exit codes for build status

## Support

For issues or questions, please contact the automation team.

## License

Proprietary - Foot Locker Automation Team