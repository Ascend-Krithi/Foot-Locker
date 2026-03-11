# Foot Locker Automation Framework

## Prerequisites
- Java 17+
- Maven 3.8+

## Run Tests Locally
```bash
mvn clean test
```

## Run Tests in Headless Mode
```bash
mvn clean test -Dheadless=true
```

## View Reports
- **Extent Report:** `test-output/ExtentReport.html`
- **Screenshots:** `test-output/screenshots/`

## CI/CD
Tests run automatically on push/PR via GitHub Actions.