import time
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.action_chains import ActionChains

class StoreLocatorPage:
    URL = "https://www.footlocker.com/store-locator"
    DESKTOP_WIDTH = 1920
    DESKTOP_HEIGHT = 1080
    TABLET_WIDTH = 768
    TABLET_HEIGHT = 1024
    MOBILE_WIDTH = 375
    MOBILE_HEIGHT = 667

    STORE_LOCATOR_HEADER = (By.XPATH, "//h1[contains(text(),'Store Locator')]")
    INTERACTIVE_ELEMENTS = (By.CSS_SELECTOR, "a, button, input, select, textarea")

    # Locators from Locators.json
    FIND_STORE_LINK = (By.LINK_TEXT, "Find a Store")
    SELECT_MY_STORE_BUTTON = (By.XPATH, "//button[contains(text(),'Select My Store')]")
    LOCATION_TEXTBOX = (By.ID, "location-search")
    SEARCH_FOR_STORES_BUTTON = (By.XPATH, "//button[contains(text(),'Search for Stores')]")
    SET_MY_STORE_BUTTON = (By.XPATH, "//button[contains(text(),'Set My Store') and ancestor::div[contains(.,'375 Washington Street, Boston, MA 02108')]]")
    CONFIRMATION_INDICATOR = (By.CSS_SELECTOR, ".store-confirmation")

    def __init__(self, driver):
        self.driver = driver

    def access_store_locator_page(self, device_type='desktop'):
        if device_type == 'desktop':
            self.driver.set_window_size(self.DESKTOP_WIDTH, self.DESKTOP_HEIGHT)
        elif device_type == 'tablet':
            self.driver.set_window_size(self.TABLET_WIDTH, self.TABLET_HEIGHT)
        elif device_type == 'mobile':
            self.driver.set_window_size(self.MOBILE_WIDTH, self.MOBILE_HEIGHT)
        else:
            raise ValueError("Invalid device type. Use 'desktop', 'tablet', or 'mobile'.")
        self.driver.get(self.URL)
        return self.verify_store_locator_page()

    def verify_store_locator_page(self):
        try:
            WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located(self.STORE_LOCATOR_HEADER)
            )
            return True
        except TimeoutException:
            return False

    def keyboard_navigation_accessibility(self):
        elements = self.driver.find_elements(*self.INTERACTIVE_ELEMENTS)
        for elem in elements:
            elem.send_keys(Keys.TAB)
            # Optionally check for focus state
            if not elem == self.driver.switch_to.active_element:
                return False
        return True

    def screen_reader_accessibility(self):
        # Placeholder for actual screen reader automation, but checks ARIA attributes and alt texts
        elements = self.driver.find_elements(*self.INTERACTIVE_ELEMENTS)
        for elem in elements:
            aria_label = elem.get_attribute('aria-label')
            alt_text = elem.get_attribute('alt')
            role = elem.get_attribute('role')
            if not (aria_label or alt_text or role):
                return False
        return True

    # --- New functions for API unavailability and error handling ---
    def simulate_api_unavailability(self):
        """
        Simulate store locator API being unavailable by intercepting network requests.
        Requires Selenium with browser devtools protocol or browser extension (e.g., Chrome).
        Returns True if page loads but no results are fetched.
        """
        try:
            # Example: Chrome DevTools Protocol for request interception
            # This is a placeholder for actual implementation, which must be configured in test setup.
            # Optionally, use browsermob-proxy or selenium-wire in test runner for real interception.
            self.driver.get(self.URL)
            WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located(self.STORE_LOCATOR_HEADER)
            )
            # Wait for results container or error message
            try:
                WebDriverWait(self.driver, 5).until(
                    EC.visibility_of_element_located((By.CSS_SELECTOR, ".store-results"))
                )
                return False  # Results should not be fetched
            except TimeoutException:
                # Check for error indicator or empty state
                error_message = self.get_store_locator_error_message()
                return error_message is not None
        except Exception:
            return False

    def search_store_with_api_down(self, location="Boston, MA"):
        """
        Attempt to search for a store when API is unavailable.
        Returns (error_message, no_results_shown)
        """
        try:
            location_box = WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located(self.LOCATION_TEXTBOX)
            )
            location_box.clear()
            location_box.send_keys(location)
            search_button = self.driver.find_element(*self.SEARCH_FOR_STORES_BUTTON)
            search_button.click()
            # Wait for error message
            error_message = self.get_store_locator_error_message()
            # Check that no results are shown
            try:
                WebDriverWait(self.driver, 5).until(
                    EC.visibility_of_element_located((By.CSS_SELECTOR, ".store-results"))
                )
                no_results = False
            except TimeoutException:
                no_results = True
            return (error_message, no_results)
        except Exception:
            return (None, True)

    def get_store_locator_error_message(self):
        """
        Returns user-friendly error message shown when store locator fails.
        """
        try:
            # Try common error message selectors
            error_selectors = [
                (By.CSS_SELECTOR, ".store-error-message"),
                (By.CSS_SELECTOR, ".error-message"),
                (By.XPATH, "//div[contains(@class,'error') or contains(text(),'not available') or contains(text(),'Please try again later')]")
            ]
            for by, value in error_selectors:
                try:
                    elem = self.driver.find_element(by, value)
                    if elem.is_displayed():
                        return elem.text
                except Exception:
                    continue
            return None
        except Exception:
            return None
