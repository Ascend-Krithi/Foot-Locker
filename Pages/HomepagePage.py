# Pages/HomepagePage.py
"""
HomepagePage
==============

This PageClass models the Foot Locker homepage and store locator interactions as specified in test cases SCRUM-15408 TS-001 TC-006 and TC-007.

Features:
- Launch homepage
- Click 'Find a Store' and 'Select My Store'
- Enter location and search for stores
- Verify store results and specific address presence

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

    def click_find_a_store(self):
        """
        Clicks the 'Find a Store' button.
        :return: None
        """
        find_store_btn = self.wait.until(EC.element_to_be_clickable(self.FIND_A_STORE_BUTTON))
        find_store_btn.click()

    def click_select_my_store(self):
        """
        Clicks the 'Select My Store' button.
        :return: None
        """
        select_store_btn = self.wait.until(EC.element_to_be_clickable(self.SELECT_MY_STORE_BUTTON))
        select_store_btn.click()
        # Wait for store locator popup
        self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))

    def enter_location_and_search(self, location):
        """
        Enters a location in the textbox and clicks 'Search for Stores'.
        :param location: String location (e.g. 'Boston, MA')
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

    def verify_store_address_present(self, address):
        """
        Verifies that a store with the exact address is present in the results.
        :param address: Store address string
        :return: True if present, else False
        """
        results_text = self.get_store_results()
        return address in results_text

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
