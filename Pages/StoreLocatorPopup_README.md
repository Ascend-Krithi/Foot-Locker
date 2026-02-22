# StoreLocatorPopup PageClass Documentation

## Executive Summary
This update ensures robust automation for Foot Locker's Store Locator functionality, covering both successful and error scenarios. The PageClasses are strictly mapped to test steps and locators.

## Detailed Analysis
- **HomePage.py:** Loads homepage and navigates to Store Locator with robust waits.
- **StoreLocatorPopup.py:** Adds/updates methods for location permission, 'Use My Location', search, store selection, and store details verification.
- **Strict adherence to Python Selenium best practices:** All methods use explicit locators, atomic actions, and exception handling.

## Implementation Guide
1. Place `HomePage.py` and `StoreLocatorPopup.py` in the `Pages` directory.
2. Use `HomePage.load_homepage()` and `HomePage.click_find_a_store()` to access Store Locator.
3. Use `StoreLocatorPopup` methods to interact with the popup, handle location, search, select store, and verify details.

## QA Report
- All methods validated for locator accuracy and mapped to test steps.
- Exception handling ensures tests do not fail abruptly.
- Methods are atomic and reusable.

## Troubleshooting Guide
- If locators change, update Locators.json and method selectors.
- If elements are not found, ensure page loads and modal visibility.
- Use browser logs for diagnosing timing/location issues.

## Future Considerations
- Generalize store confirmation for any address.
- Add dynamic waits for modal and search results.
- Expand error handling for different error messages.

## Example Usage
```python
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup

home_page = HomePage(driver)
store_locator = StoreLocatorPopup(driver)

home_page.load_homepage()
home_page.click_find_a_store()
store_locator.wait_for_popup()
store_locator.allow_location_permission()
store_locator.click_use_my_location()
assert store_locator.are_store_results_displayed()
store_locator.enter_location("Boston, MA")
store_locator.click_search_for_stores()
assert store_locator.is_store_address_present_in_results("375 Washington Street, Boston, MA 02108")
store_locator.click_store_by_address("375 Washington Street, Boston, MA 02108")
assert store_locator.verify_store_details_popup("375 Washington Street, Boston, MA 02108")
```
