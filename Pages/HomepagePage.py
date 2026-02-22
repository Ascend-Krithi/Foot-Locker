# Pages/HomepagePage.py
"""
HomepagePage
============

This PageClass models the Foot Locker homepage and store locator interactions as specified in test cases SCRUM-15408 TS-SL-002 TC-001, TS-SL-003 TC-001, TS-SL-006 TC-001, and TS-SL-007 TC-001.

Features:
- Launch homepage
- Navigate directly to Store Locator page
- Enter location (city or ZIP) and search for stores
- Verify store results
- Handle browser location permission and automate 'Use My Location'
- Select a store by address and verify its details (address, hours, contact info)

Strict code integrity, robust locator mapping, and comprehensive docstrings are included for downstream automation.
"""

from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.action_chains import ActionChains

class HomepagePage:
    """
    PageClass for Foot Locker Homepage and Store Locator interactions.
    """

    # Locators from Locators.json
    FIND_A_STORE_BUTTON = (By.XPATH, "//button[contains(text(), 'Find a Store')]")
    SELECT_MY_STORE_BUTTON = (By.XPATH, "//button[contains(text(), 'Select My Store')]")
    LOCATION_TEXTBOX = (By.XPATH, "//input[@id='store-locator-input']")
    SEARCH_FOR_STORES_BUTTON = (By.XPATH, "//button[contains(text(), 'Search for Stores')]")
    STORE_RESULTS_POPUP = (By.XPATH, "//div[@id='store-locator-results']")
    NO_STORES_FOUND_MESSAGE = (By.XPATH, "//div[contains(text(), 'No stores found near this location')]")
    USE_MY_LOCATION_BUTTON = (By.XPATH, "//button[contains(text(), 'Use My Location')]")

    def __init__(self, driver, wait_time=10):
        self.driver = driver
        self.wait = WebDriverWait(driver, wait_time)

    def launch_homepage(self, url="https://www.footlocker.com/"):
        """
        Launches the Foot Locker homepage and waits for the Find a Store button.
        """
        self.driver.get(url)
        try:
            self.wait.until(EC.presence_of_element_located(self.FIND_A_STORE_BUTTON))
        except TimeoutException:
            raise AssertionError("Homepage did not load as expected.")

    def navigate_to_store_locator(self, url="https://www.footlocker.com/store-locator"):
        """
        Navigates directly to the Store Locator page and waits for the location textbox.
        """
        self.driver.get(url)
        try:
            self.wait.until(EC.presence_of_element_located(self.LOCATION_TEXTBOX))
        except TimeoutException:
            raise AssertionError("Store Locator page did not load as expected.")

    def enter_location_and_search(self, location):
        """
        Enters the provided location (city or ZIP) and searches for stores.
        """
        location_box = self.wait.until(EC.element_to_be_clickable(self.LOCATION_TEXTBOX))
        location_box.clear()
        location_box.send_keys(location)
        search_btn = self.wait.until(EC.element_to_be_clickable(self.SEARCH_FOR_STORES_BUTTON))
        search_btn.click()
        self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))

    def get_store_results(self):
        """
        Returns the text of the store results popup.
        """
        results_popup = self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))
        return results_popup.text

    def verify_store_results_displayed(self):
        """
        Verifies that the store results popup is visible.
        """
        try:
            self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))
            return True
        except TimeoutException:
            return False

    def verify_no_stores_found(self):
        """
        Verifies that the 'No stores found' message is visible.
        """
        try:
            self.wait.until(EC.visibility_of_element_located(self.NO_STORES_FOUND_MESSAGE))
            return True
        except TimeoutException:
            return False

    def handle_location_permission_and_use_my_location(self):
        """
        Handles browser location permission and clicks the 'Use My Location' button.
        Validates that nearby stores are displayed.

        Note: Actual browser location permission must be set via browser profile or capabilities.
        """
        try:
            use_location_btn = self.wait.until(EC.element_to_be_clickable(self.USE_MY_LOCATION_BUTTON))
            use_location_btn.click()
        except TimeoutException:
            raise AssertionError("'Use My Location' button not found or not clickable.")

        # Wait for store results to appear
        if not self.verify_store_results_displayed():
            raise AssertionError("Store results did not display after using location.")

    def select_store_by_address_and_verify_details(self, address):
        """
        Selects a store from search results by address and verifies its details.

        Args:
            address (str): The store address to select.

        Returns:
            dict: Store details including address, hours, and contact info.

        Raises:
            AssertionError: If store or its details are not found.
        """
        # Wait for store results popup
        results_popup = self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))

        # Find store by address in results
        store_xpath = f"//div[@id='store-locator-results']//div[contains(text(), '{address}')]")
        try:
            store_element = self.wait.until(EC.element_to_be_clickable((By.XPATH, store_xpath)))
        except TimeoutException:
            raise AssertionError(f"Store with address '{address}' not found in search results.")

        # Click the store to view details
        store_element.click()

        # Wait for store details popup/page
        details_xpath = f"//div[contains(@class, 'store-details') and contains(., '{address}')]")
        try:
            details_element = self.wait.until(EC.visibility_of_element_located((By.XPATH, details_xpath)))
        except TimeoutException:
            raise AssertionError("Store details popup/page did not appear.")

        # Extract details
        details = {}
        details['address'] = address

        # Extract hours (example XPath, may need adjustment based on actual markup)
        try:
            hours_element = details_element.find_element(By.XPATH, ".//div[contains(@class, 'store-hours')]")
            details['hours'] = hours_element.text
        except Exception:
            details['hours'] = None

        # Extract contact info (example XPath, may need adjustment based on actual markup)
        try:
            contact_element = details_element.find_element(By.XPATH, ".//div[contains(@class, 'store-contact')]")
            details['contact'] = contact_element.text
        except Exception:
            details['contact'] = None

        # Validate details
        if not details['address'] or not details['hours'] or not details['contact']:
            raise AssertionError("Store details incomplete: address, hours, or contact info missing.")

        return details
