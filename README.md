# Foot Locker Automation Framework

## Overview
Complete Java Selenium TestNG automation framework for Foot Locker with 64 test classes covering Store Locator, Eco Home Hub, Marketplace, and Platform functionality.

## Technology Stack
- **Java**: 17
- **Selenium WebDriver**: 4.21.0
- **TestNG**: 7.10.2
- **Extent Reports**: 5.1.1
- **WebDriver Manager**: 5.9.2
- **Lombok**: 1.18.32
- **Maven**: Build and dependency management

## Project Structure
```
footlocker-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/fl/automation/
│   │           ├── core/
│   │           │   └── DriverFactory.java
│   │           ├── helpers/
│   │           │   └── StoreLocatorHelper.java
│   │           ├── pages/
│   │           │   └── HomePage.java
│   │           └── utils/
│   │               ├── ExtentManager.java
│   │               └── ScreenshotUtil.java
│   └── test/
│       ├── java/
│       │   └── com/fl/automation/
│       │       ├── core/
│       │       │   └── BaseTest.java
│       │       ├── listeners/
│       │       │   └── TestListener.java
│       │       └── tests/
│       │           └── [64 test classes]
│       └── resources/
│           └── testng.xml
├── pom.xml
└── README.md
```

## Test Coverage

### Store Locator Tests (16 tests)
- Verify Find A Store popup and flow
- Search stores by location
- Verify specific store addresses
- Set and verify preferred store
- Verify preferred store persistence across pages

### Eco Home Hub Tests (20 tests)
- Customer registration and loan application
- Installer search and project lead management
- Admin installer approval and certification vetting
- Installer login and project lead viewing
- Data encryption verification
- Production deployment and monitoring
- Analytics tracking
- Form validation
- Installer onboarding

### Marketplace Tests (18 tests)
- Marketplace home page verification
- Search by keyword and category
- View project details and contact owner
- User login, registration, and logout
- User dashboard and profile management
- Project application and review submission
- Eco-certification details
- Project sharing and favorites
- Help and support access

### Platform Tests (10 tests)
- Manager login and dashboard verification
- Dashboard modules and UI/UX verification
- Staff incident reporting
- Incident form validation
- Brand consistency verification
- Interactive elements styling
- Messages and popups branding

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Chrome browser (for headless execution)

### Installation
1. Clone the repository
2. Navigate to project directory
3. Run `mvn clean install` to download dependencies

## Execution

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
mvn clean test -Dtest=TS001_TC001_VerifyFindAStorePopup
```

### Run with Custom Browser
```bash
mvn clean test -Dbrowser=chrome
```

### Parallel Execution
Tests are configured to run in parallel with 3 threads by default in testng.xml

## Reporting

### Extent Reports
- HTML reports are generated in `test-output/` directory
- Reports include test execution details, screenshots on failure, and execution timeline

### TestNG Reports
- Default TestNG reports available in `test-output/` directory

## Key Features

### Robust Locator Strategy
- Multiple fallback locators for each element
- Case-insensitive XPath expressions
- CSS selector alternatives

### CI/CD Ready
- Headless browser execution
- Chrome options optimized for CI environments
- No manual intervention required

### Error Handling
- JavaScript click fallback for stale elements
- WebDriverWait with configurable timeouts (40-60s)
- Screenshot capture on test failure

### Page Object Model
- Clean separation of test logic and page interactions
- Reusable helper classes
- Maintainable test structure

## Coding Standards

### Wait Strategy
- WebDriverWait with explicit waits (40-60s timeout)
- No Thread.sleep() usage
- ExpectedConditions for element interactions

### Click Strategy
```java
try {
    element.click();
} catch (Exception e) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", element);
}
```

### Assertions
- TestNG assertions only
- Descriptive error messages
- Assert.assertTrue() with custom messages

## Browser Configuration

### Chrome Options (CI-Safe)
- --headless
- --no-sandbox
- --disable-dev-shm-usage
- --disable-gpu
- --window-size=1920,1080
- --remote-allow-origins=*
- --disable-notifications
- --incognito

## Maintenance

### Adding New Tests
1. Create test class in `src/test/java/com/fl/automation/tests/`
2. Extend `BaseTest`
3. Add test method with `@Test` annotation
4. Update `testng.xml` to include new test class

### Updating Locators
1. Locate page object or helper class
2. Update locator list with fallback options
3. Test locator strategy on target application

## Troubleshooting

### Common Issues

**Issue**: Element not found
- **Solution**: Check fallback locators, increase wait timeout

**Issue**: Stale element reference
- **Solution**: JavaScript click fallback is implemented

**Issue**: Tests fail in CI
- **Solution**: Verify Chrome options are properly configured

## Contact
For questions or issues, please contact the automation team.

## License
Proprietary - Foot Locker Internal Use Only
