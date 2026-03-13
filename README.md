# Foot Locker Selenium TestNG Automation Framework

## Overview
Complete Java Selenium TestNG automation framework for Foot Locker with 47 comprehensive test cases covering Store Locator, Eco Home Hub, and Marketplace functionality.

## Framework Structure
```
footlocker-selenium-framework/
├── pom.xml
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
│       │           └── [47 test classes]
│       └── resources/
│           └── testng.xml
└── README.md
```

## Technology Stack
- **Java**: 17
- **Selenium WebDriver**: 4.21.0
- **TestNG**: 7.10.2
- **Extent Reports**: 5.1.1
- **WebDriverManager**: 5.9.2
- **Maven**: 3.x

## Test Coverage (47 Tests)

### Store Locator Tests (9 tests)
- TC_3193: Verify Find a Store popup and Select My Store link
- TC_3194: Verify Store locator popup with Location textbox and Search button
- TC_3195: Search for stores in Boston, MA
- TC_4170: Find a Store complete flow
- TC_4171: Search stores Boston MA
- TC_4172: Verify specific store address
- TC_4173: Set My Store
- TC_4174: Verify store confirmation
- TC_4175: Verify store persistence

### Eco Home Hub Tests (20 tests)
- Customer registration and loan application
- Installer search and project lead management
- Admin approval and certification vetting
- Installer login and project lead viewing
- Data encryption (at rest and in transit)
- Production deployment and monitoring
- Analytics tracking
- Form validation
- Certification requirements

### Marketplace Tests (18 tests)
- Home page load
- Search by keyword and category
- Project details and contact
- User authentication (login/logout)
- User registration and dashboard
- Profile management
- Reviews and certifications
- Social sharing
- Favorites and help resources

## Setup Instructions

### Prerequisites
1. Java JDK 17 or higher
2. Maven 3.6 or higher
3. Chrome browser (latest version)

### Installation
1. Clone the repository
2. Navigate to project directory
3. Run: `mvn clean install`

### Running Tests

#### Run all tests:
```bash
mvn clean test
```

#### Run specific test suite:
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

#### Run in headless mode:
```bash
mvn clean test -Dheadless=true
```

#### Run with specific browser:
```bash
mvn clean test -Dbrowser=chrome
```

## Framework Features

### Core Features
- **ThreadLocal WebDriver**: Thread-safe parallel execution
- **WebDriverManager**: Automatic driver management
- **CI/CD Ready**: Headless mode support for Jenkins/GitHub Actions
- **Extent Reports**: Detailed HTML reports with screenshots
- **Page Object Model**: Maintainable and scalable structure
- **Explicit Waits**: Robust synchronization (40-60s timeouts)
- **JavaScript Fallback**: Reliable element interactions

### Locator Strategy
- Multiple fallback locators for reliability
- Exact locators from requirements:
  - HEADER_FIND_A_STORE: linkText, cssSelector, xpath
  - SELECT_MY_STORE: xpath variations
  - LOCATOR_INPUT: cssSelector with case-insensitive matching
  - LOCATOR_SEARCH_BTN: xpath with case-insensitive text
  - STORE_RESULT_CARDS: data-qa attribute
  - STORE_ADDRESS_INSIDE_CARD: data-qa attribute

### Reporting
- Extent Reports with screenshots on failure
- TestNG XML reports
- Console logging
- Reports location: `test-output/ExtentReport_[timestamp].html`

## Configuration

### TestNG Configuration
- Parallel execution: 3 threads
- Test groups: Store Locator, Eco Home Hub, Marketplace
- Listeners: TestListener for reporting

### Browser Options
- Chrome (default): Headless support, CI-safe options
- Firefox: Supported
- Edge: Supported

## Best Practices Implemented
- No Thread.sleep() - only WebDriverWait
- TestNG assertions only
- All locators in Page/Helper classes
- JavaScript click fallback for reliability
- Proper exception handling
- Screenshot capture on failure
- Thread-safe driver management

## CI/CD Integration

### GitHub Actions Example
```yaml
name: Selenium Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      - name: Run tests
        run: mvn clean test -Dheadless=true
      - name: Upload reports
        uses: actions/upload-artifact@v2
        with:
          name: test-reports
          path: test-output/
```

## Troubleshooting

### Common Issues
1. **WebDriver not found**: WebDriverManager handles this automatically
2. **Element not found**: Multiple fallback locators implemented
3. **Timeout issues**: Increased wait times (40-60s)
4. **Click intercepted**: JavaScript click fallback implemented

## Maintenance
- Update dependencies in pom.xml regularly
- Review and update locators if UI changes
- Add new tests following existing patterns
- Keep README updated with new test cases

## Contact
For issues or questions, please contact the automation team.

## License
Proprietary - Foot Locker Internal Use Only