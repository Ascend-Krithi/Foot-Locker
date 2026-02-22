# FootLocker Store Locator Automation

This project automates the testing of the FootLocker Store Locator feature using Selenium WebDriver and TestNG.

## Project Structure
- `pom.xml`: Maven dependencies and build configuration
- `testng.xml`: TestNG suite configuration
- `src/main/java/pages`: Page Object Model classes
- `src/main/java/utils`: Utility classes
- `src/test/java/tests`: Test classes
- `src/test/java/utils`: Test listeners and utilities
- `src/main/resources/config.properties`: Configuration file

## How to Run
1. Install Maven and Java 11+
2. Update `config.properties` if needed
3. Run tests with:
   ```
   mvn clean test
   ```
