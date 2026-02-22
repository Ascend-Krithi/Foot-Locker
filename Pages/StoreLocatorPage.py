# StoreLocatorPage.py
"""
Selenium Page Object for Foot Locker Store Locator functionality.
Covers navigation, location entry, search, and result verification.
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
            EC.element_to_be_clickable(self.locators["search_button"])
        )
        search_btn.click()

    def get_search_results(self):
        """
        Returns a list of store results displayed after search.
        :return: List of dicts with store info (e.g., name, address)
        """
        try:
            WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located(self.locators["results_container"])
            )
            results = self.driver.find_elements(*self.locators["store_result_items"])
            store_list = []
            for item in results:
                try:
                    name = item.find_element(*self.locators["store_name"]).text
                except NoSuchElementException:
                    name = ""
                try:
                    address = item.find_element(*self.locators["store_address"]).text
                except NoSuchElementException:
                    address = ""
                store_list.append({"name": name, "address": address})
            return store_list
        except TimeoutException:
            return []

# Example locators mapping from Locators.json:
# locators = {
#     "location_textbox": (By.ID, "store-locator-input"),
#     "search_button": (By.XPATH, "//button[@data-testid='store-locator-search']"),
#     "results_container": (By.ID, "store-results-container"),
#     "store_result_items": (By.CLASS_NAME, "store-result-item"),
#     "store_name": (By.CLASS_NAME, "store-name"),
#     "store_address": (By.CLASS_NAME, "store-address")
# }
