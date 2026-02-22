"""
StoreLocatorPage.py

Page Object for Footlocker Store Locator page.
Verifies display and functionality across desktop, tablet, and mobile browsers.
Includes accessibility tests for keyboard navigation and screen reader support.

Test Steps:
1. Access Store Locator page on desktop, tablet, and mobile browsers.
2. Navigate to Store Locator page.
3. Test keyboard navigation through all interactive elements.
4. Test with a screen reader.

Author: Automation Team
"""

from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

class StoreLocatorPage:
    """
    Page Object for the Store Locator page at https://www.footlocker.com/store-locator.
    """

    URL = "https://www.footlocker.com/store-locator"

    # Locators (from Locators.json, example structure)
    LOCATORS = {
        # Add all relevant locators for Store Locator page here if available
        # Example:
        "SEARCH_INPUT": {"by": By.ID, "value": "store-search-location"},
        "SEARCH_BUTTON": {"by": By.XPATH, "value": "//button[contains(text(), 'Search for Stores')]"},
        "RESULT_LIST": {"by": By.ID, "value": "store-search-results"},
        "INTERACTIVE_ELEMENTS": {"by": By.CSS_SELECTOR, "value": "a, button, input, [tabindex]"},
        # Add more as needed
    }

    def __init__(self, driver: WebDriver):
        """
        Initializes StoreLocatorPage with a Selenium WebDriver instance.
        """
        self.driver = driver
        self.wait = WebDriverWait(driver, 20)

    def load(self, device_type="desktop"):
        """
        Loads the Store Locator page and sets viewport for device type.

        Args:
            device_type (str): 'desktop', 'tablet', or 'mobile'
        """
        self.driver.get(self.URL)
        if device_type == "tablet":
            self.driver.set_window_size(768, 1024)
        elif device_type == "mobile":
            self.driver.set_window_size(375, 667)
        else:
            self.driver.set_window_size(1366, 768)
        self.wait.until(EC.presence_of_element_located((self.LOCATORS["SEARCH_INPUT"]["by"], self.LOCATORS["SEARCH_INPUT"]["value"])))

    def is_displayed(self):
        """
        Verifies Store Locator page is displayed.

        Returns:
            bool: True if page is loaded and main elements are present.
        """
        try:
            search_input = self.wait.until(EC.presence_of_element_located((self.LOCATORS["SEARCH_INPUT"]["by"], self.LOCATORS["SEARCH_INPUT"]["value"])))
            search_button = self.wait.until(EC.presence_of_element_located((self.LOCATORS["SEARCH_BUTTON"]["by"], self.LOCATORS["SEARCH_BUTTON"]["value"])))
            return search_input.is_displayed() and search_button.is_displayed()
        except Exception:
            return False

    def verify_functionality(self):
        """
        Verifies Store Locator functionality (search).

        Returns:
            bool: True if search returns results.
        """
        try:
            search_input = self.driver.find_element(self.LOCATORS["SEARCH_INPUT"]["by"], self.LOCATORS["SEARCH_INPUT"]["value"])
            search_input.clear()
            search_input.send_keys("New York")
            search_button = self.driver.find_element(self.LOCATORS["SEARCH_BUTTON"]["by"], self.LOCATORS["SEARCH_BUTTON"]["value"])
            search_button.click()
            result_list = self.wait.until(EC.presence_of_element_located((self.LOCATORS["RESULT_LIST"]["by"], self.LOCATORS["RESULT_LIST"]["value"])))
            return result_list.is_displayed()
        except Exception:
            return False

    def verify_keyboard_navigation(self):
        """
        Tests keyboard navigation through all interactive elements.

        Returns:
            bool: True if all interactive elements are reachable via Tab and actionable.
        """
        try:
            elements = self.driver.find_elements(self.LOCATORS["INTERACTIVE_ELEMENTS"]["by"], self.LOCATORS["INTERACTIVE_ELEMENTS"]["value"])
            self.driver.switch_to.active_element
            reachable = []
            for _ in range(len(elements) + 5):  # Allow extra tabs for cycling
                active = self.driver.switch_to.active_element
                if active in elements and active not in reachable:
                    reachable.append(active)
                active.send_keys(Keys.TAB)
                time.sleep(0.1)
            return len(reachable) >= len(elements)
        except Exception:
            return False

    def verify_screen_reader_support(self):
        """
        Verifies screen reader support by ensuring elements have accessible names/roles.

        Returns:
            bool: True if all interactive elements have ARIA labels or accessible names.
        """
        try:
            elements = self.driver.find_elements(self.LOCATORS["INTERACTIVE_ELEMENTS"]["by"], self.LOCATORS["INTERACTIVE_ELEMENTS"]["value"])
            for elem in elements:
                aria_label = elem.get_attribute("aria-label")
                aria_role = elem.get_attribute("role")
                name = elem.get_attribute("name")
                alt = elem.get_attribute("alt")
                if not (aria_label or aria_role or name or alt):
                    return False
            return True
        except Exception:
            return False

    def run_accessibility_checks(self):
        """
        Runs all accessibility checks: keyboard navigation and screen reader support.

        Returns:
            dict: Results of accessibility checks.
        """
        return {
            "keyboard_navigation": self.verify_keyboard_navigation(),
            "screen_reader_support": self.verify_screen_reader_support()
        }
