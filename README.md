# Foot Locker Selenium TestNG Automation Framework

## Overview
Complete Java Selenium TestNG automation framework for Foot Locker and Eco Home Hub with 47 comprehensive test cases.

## Framework Structure
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
│   │                   │   ├── HomePage.java
│   │                   │   ├── EcoHomeRegistrationPage.java
│   │                   │   ├── EcoHomeLoanPage.java
│   │                   │   ├── EcoHomeInstallerPage.java
│   │                   │   ├── AdminDashboardPage.java
│   │                   │   ├── InstallerPortalPage.java
│   │                   │   └── MarketplacePage.java
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
│       │                   ├── TS001_TC001_FindAStorePopup.java
│       │                   ├── TS001_TC002_StoreLocatorElements.java
│       │                   ├── ... (47 test classes total)
│       │                   └── TS029_TC001_VerifyStorePersistenceAcrossPages.java
│       └── resources/
│           └── testng.xml
└── test-output/
```

## Technologies Used
- **Java**: 17
- **Selenium WebDriver**: 4.21.0
- **TestNG**: 7.10.2
- **WebDriverManager**: 5.9.2
- **Extent Reports**: 5.1.1
- **Lombok**: 1.18.32
- **Maven**: Build and dependency management

## Test Coverage (47 Test Cases)

### Foot Locker Store Locator (9 tests)
- TC_3193: Find a Store popup verification
- TC_3194: Store locator elements verification
- TC_3195: Search stores in Boston MA
- TC_4170-4175: Complete store locator flow, address verification, preferred store

### Eco Home Hub Registration & Loan (4 tests)
- TC_4104: Customer registration
- TC_4105: Register, login, apply for loan
- TC_4106: Register, loan, installer search
- TC_4107: Complete end-to-end flow

### Admin & Installer Portal (5 tests)
- TC_4108: Admin login and approve installer
- TC_4109: Admin vet certifications
- TC_4110: Verify installer in marketplace
- TC_4111: Installer portal login
- TC_4112: View project leads

### Security & Deployment (4 tests)
- TC_4113: Data encryption at rest
- TC_4114: Data encryption in transit
- TC_4115: Production deployment
- TC_4116: Monitoring and error tracking

### Analytics (3 tests)
- TC_4117: Loan applications started metric
- TC_4118: Loan applications completed metric
- TC_4119: Leads sent to installers metric

### Negative & Boundary Tests (4 tests)
- TC_4120: Loan with missing fields
- TC_4121: Loan with invalid values
- TC_4122: Minimum certifications
- TC_4123: Maximum certifications

### Marketplace (18 tests)
- TC_4139: Homepage load
- TC_4140-4141: Search by keyword and category
- TC_4142-4143: View and contact project
- TC_4144-4156: Login, registration, dashboard, profile, reviews, certifications, sharing, favorites, help

## Setup Instructions

### Prerequisites
- Java JDK 17 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser

### Installation
1. Clone the repository
```bash
git clone <repository-url>
cd footlocker-selenium-framework
```

2. Install dependencies
```bash
mvn clean install -DskipTests
```

### Running Tests

#### Run all tests
```bash
mvn clean test
```

#### Run specific test suite
```bash
mvn clean test -Dtest=TS001_TC001_FindAStorePopup
```

#### Run with specific browser
```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

#### Run TestNG XML
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

## Framework Features

### Page Object Model (POM)
- Separation of test logic and page elements
- Reusable page classes
- Maintainable and scalable structure

### Locator Strategy
- Multiple fallback locators for robustness
- Priority order: linkText → cssSelector → xpath
- All locators centralized in Page/Helper classes

### WebDriver Management
- Automatic driver setup using WebDriverManager
- ThreadLocal driver for parallel execution
- Chrome options: incognito, maximized, notifications disabled

### Waits and Synchronization
- Explicit waits (40-60 seconds)
- No Thread.sleep() usage
- JavaScript click fallback for stale elements

### Reporting
- Extent Reports with detailed test execution logs
- Screenshots on test failure
- Timestamped reports in test-output directory

### Test Listeners
- Automatic screenshot capture on failure
- Test execution logging
- Report generation

## Best Practices Implemented
- ✅ No hardcoded waits
- ✅ JavaScript click fallback
- ✅ Multiple locator strategies
- ✅ Page Object Model
- ✅ TestNG assertions only
- ✅ Extent Reports integration
- ✅ Screenshot on failure
- ✅ Parallel test execution support
- ✅ Browser configuration via parameters

## Test Execution Reports
After test execution, reports are generated in:
- `test-output/ExtentReport_<timestamp>.html` - Detailed HTML report
- `test-output/screenshots/` - Failure screenshots

## Troubleshooting

### Common Issues
1. **WebDriver not found**: WebDriverManager handles this automatically
2. **Element not found**: Multiple fallback locators implemented
3. **Stale element**: JavaScript click fallback implemented
4. **Timeout**: Increased wait times (40-60s) configured

## Contributing
1. Follow existing code structure
2. Add tests to appropriate test suite in testng.xml
3. Use Page Object Model pattern
4. Include multiple fallback locators
5. Add proper test descriptions

## License
Proprietary - Foot Locker Automation Framework

## Contact
For questions or issues, contact the QA Automation team.

---
**Framework Version**: 1.0.0  
**Last Updated**: 2024  
**Total Test Cases**: 47  
**Test Coverage**: Foot Locker Store Locator + Eco Home Hub Complete Flows
