# StoreLocatorPage.py
"""
Selenium Page Object for Foot Locker Store Locator functionality.
Covers navigation, location entry, search, result verification, and error handling for 'no stores found'.
Strict adherence to Python Selenium best practices.
"""

from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException, NoSuchElementException

class StoreLocatorPage:
    """
    Page Object for Foot Locker Store Locator Page.
    """
    def __init__(self, driver, locators):
        """
        :param driver: Selenium WebDriver instance
        :param locators: Dict mapping locator names to tuples (By, value)
        """
        self.driver = driver
        self.locators = locators

    def navigate_to_store_locator(self, url="https://www.footlocker.com/store-locator"):
        """
        Navigates to the Store Locator page.
        :param url: Store Locator page URL
        """
        self.driver.get(url)
        try:
            WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located(self.locators["location_textbox"])
            )
        except TimeoutException:
            raise Exception("Store Locator page did not load within timeout.")

    def enter_location(self, location):
        """
        Enters a city or ZIP code in the location textbox.
        :param location: String (city or ZIP)
        """
        textbox = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.locators["location_textbox"])
        )
        textbox.clear()
        textbox.send_keys(location)

    def click_search(self):
        """
        Clicks the Search for Stores button.
        """
        search_btn = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.locators["search_for_stores_button"])
        )
        search_btn.click()

    def is_store_search_successful(self):
        """
        Verifies if confirmation indicator is present after search (successful search).
        :return: True if confirmation indicator is visible, False otherwise
        """
        try:
            WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located(self.locators["confirmation_indicator"])
            )
            return True
        except TimeoutException:
            return False

    def is_no_stores_found(self):
        """
        Verifies if error message is displayed indicating no stores found.
        :return: True if no stores found indicator is visible, False otherwise
        """
        try:
            error_indicator = WebDriverWait(self.driver, 5).until(
                EC.visibility_of_element_located((By.XPATH, "//div[contains(text(),'No stores found')]") )
            )
            return error_indicator.is_displayed()
        except TimeoutException:
            return False

    def click_use_my_location(self):
        """
        Clicks the 'Use My Location' button and handles browser geolocation permission prompt.
        Relies on the browser being configured to allow location access, or test setup to auto-accept permission.
        :raises: Exception if the button is not found or not clickable.
        """
        try:
            use_location_btn = WebDriverWait(self.driver, 10).until(
                EC.element_to_be_clickable((By.XPATH, "//button[contains(text(),'Use My Location') or contains(text(),'Use my location') or contains(.,'Use My Location') or contains(.,'Use my location') ]"))
            )
            use_location_btn.click()
        except TimeoutException:
            raise Exception("'Use My Location' button not found or not clickable within timeout.")
        # Note: Handling browser's location permission prompt is typically done via browser profile/capabilities
        # or external tools. Selenium cannot interact with OS-level popups directly. Ensure test environment auto-allows location.

    def verify_nearby_stores_displayed(self):
        """
        Verifies if a list of nearby stores is displayed after using location.
        :return: True if at least one store result is visible, False otherwise.
        """
        try:
            # Assume store results have a common locator, e.g., a class or data attribute
            store_results = WebDriverWait(self.driver, 10).until(
                EC.presence_of_all_elements_located((By.CSS_SELECTOR, ".store-result, .StoreResult"))
            )
            return len(store_results) > 0
        except TimeoutException:
            return False
