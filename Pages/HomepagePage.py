# Pages/HomepagePage.py
"""
HomepagePage
============

This PageClass models the Foot Locker homepage and store locator interactions as specified in test cases SCRUM-15408 TS-SL-002 TC-001 and TS-SL-003 TC-001.

Features:
- Launch homepage
- Navigate directly to Store Locator page
- Enter location (city or ZIP) and search for stores
- Verify store results

Strict code integrity, robust locator mapping, and comprehensive docstrings are included for downstream automation.
"""

from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException

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

    def __init__(self, driver, wait_time=10):
        """
        Initialize with Selenium WebDriver instance.
        :param driver: Selenium WebDriver
        :param wait_time: Default wait time for elements
        """
        self.driver = driver
        self.wait = WebDriverWait(driver, wait_time)

    def launch_homepage(self, url="https://www.footlocker.com/"):
        """
        Launches the Foot Locker homepage.
        :param url: Homepage URL
        :return: None
        """
        self.driver.get(url)
        try:
            self.wait.until(EC.presence_of_element_located(self.FIND_A_STORE_BUTTON))
        except TimeoutException:
            raise AssertionError("Homepage did not load as expected.")

    def navigate_to_store_locator(self, url="https://www.footlocker.com/store-locator"):
        """
        Navigates directly to the Foot Locker Store Locator page.
        :param url: Store Locator URL
        :return: None
        """
        self.driver.get(url)
        try:
            self.wait.until(EC.presence_of_element_located(self.LOCATION_TEXTBOX))
        except TimeoutException:
            raise AssertionError("Store Locator page did not load as expected.")

    def enter_location_and_search(self, location):
        """
        Enters a location (city or ZIP) in the textbox and clicks 'Search for Stores'.
        :param location: String location (e.g. 'Boston, MA' or '02108')
        :return: None
        """
        location_box = self.wait.until(EC.element_to_be_clickable(self.LOCATION_TEXTBOX))
        location_box.clear()
        location_box.send_keys(location)
        search_btn = self.wait.until(EC.element_to_be_clickable(self.SEARCH_FOR_STORES_BUTTON))
        search_btn.click()
        # Wait for results popup
        self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))

    def get_store_results(self):
        """
        Returns the text content of the store results popup.
        :return: String text of store results
        """
        results_popup = self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))
        return results_popup.text

    def verify_store_results_displayed(self):
        """
        Verifies that the store results popup is displayed.
        :return: True if displayed, else False
        """
        try:
            self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))
            return True
        except TimeoutException:
            return False

    def verify_no_stores_found(self):
        """
        Verifies if the 'No stores found near this location' message is displayed.
        :return: True if displayed, else False
        """
        try:
            self.wait.until(EC.visibility_of_element_located(self.NO_STORES_FOUND_MESSAGE))
            return True
        except TimeoutException:
            return False
