# Pages/StoreLocatorPage.py
"""
StoreLocatorPage
================

This PageClass models advanced Store Locator page interactions for Foot Locker, including toggling map view and handling pagination.

Features:
- Toggle between list and map views
- Navigate store result pages (pagination)
- Validate map pins and store results

Strict code integrity, robust locator mapping, and comprehensive docstrings included for downstream automation.
"""

from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException

class StoreLocatorPage:
    """
    PageClass for Foot Locker Store Locator advanced interactions: map view & pagination.
    """

    # Locators from Locators.json and inferred for map/pagination
    MAP_VIEW_TOGGLE_BUTTON = (By.XPATH, "//button[contains(text(), 'Map') or @aria-label='Map View']")  # inferred
    LIST_VIEW_TOGGLE_BUTTON = (By.XPATH, "//button[contains(text(), 'List') or @aria-label='List View']")  # inferred
    STORE_RESULTS_POPUP = (By.XPATH, "//div[@id='store-locator-results']")
    MAP_PIN = (By.XPATH, "//div[contains(@class, 'store-pin')]" )  # inferred
    PAGINATION_NEXT_BUTTON = (By.XPATH, "//button[contains(@aria-label, 'Next') or contains(text(), 'Next')]" )  # inferred
    PAGINATION_PREV_BUTTON = (By.XPATH, "//button[contains(@aria-label, 'Previous') or contains(text(), 'Previous')]" )  # inferred
    PAGINATION_PAGE_NUMBER = (By.XPATH, "//button[@aria-label='Page']" )  # inferred

    def __init__(self, driver, wait_time=10):
        self.driver = driver
        self.wait = WebDriverWait(driver, wait_time)

    def toggle_map_view(self):
        """
        Toggles to the map view and validates that map pins are displayed.
        """
        try:
            map_btn = self.wait.until(EC.element_to_be_clickable(self.MAP_VIEW_TOGGLE_BUTTON))
            map_btn.click()
        except TimeoutException:
            raise AssertionError("Map view toggle button not found or not clickable.")

        # Wait for map pins to appear
        try:
            self.wait.until(EC.visibility_of_element_located(self.MAP_PIN))
        except TimeoutException:
            raise AssertionError("Map pins did not display after toggling map view.")

    def toggle_list_view(self):
        """
        Toggles to the list view and validates that store results are displayed.
        """
        try:
            list_btn = self.wait.until(EC.element_to_be_clickable(self.LIST_VIEW_TOGGLE_BUTTON))
            list_btn.click()
        except TimeoutException:
            raise AssertionError("List view toggle button not found or not clickable.")

        # Wait for store results popup
        try:
            self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))
        except TimeoutException:
            raise AssertionError("Store results did not display after toggling list view.")

    def go_to_next_page(self):
        """
        Clicks the pagination 'Next' button and waits for store results to update.
        """
        try:
            next_btn = self.wait.until(EC.element_to_be_clickable(self.PAGINATION_NEXT_BUTTON))
            next_btn.click()
        except TimeoutException:
            raise AssertionError("Pagination Next button not found or not clickable.")

        # Wait for store results popup to update
        try:
            self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))
        except TimeoutException:
            raise AssertionError("Store results did not update after clicking Next.")

    def go_to_previous_page(self):
        """
        Clicks the pagination 'Previous' button and waits for store results to update.
        """
        try:
            prev_btn = self.wait.until(EC.element_to_be_clickable(self.PAGINATION_PREV_BUTTON))
            prev_btn.click()
        except TimeoutException:
            raise AssertionError("Pagination Previous button not found or not clickable.")

        try:
            self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))
        except TimeoutException:
            raise AssertionError("Store results did not update after clicking Previous.")

    def go_to_page(self, page_number):
        """
        Clicks a specific page number in pagination and waits for store results to update.
        """
        page_btn_xpath = f"//button[@aria-label='Page {page_number}']"
        try:
            page_btn = self.wait.until(EC.element_to_be_clickable((By.XPATH, page_btn_xpath)))
            page_btn.click()
        except TimeoutException:
            raise AssertionError(f"Pagination button for page {page_number} not found or not clickable.")

        try:
            self.wait.until(EC.visibility_of_element_located(self.STORE_RESULTS_POPUP))
        except TimeoutException:
            raise AssertionError(f"Store results did not update after clicking page {page_number}.")

    def validate_map_pins(self):
        """
        Validates that map pins are visible on the map view.
        """
        try:
            pins = self.wait.until(EC.visibility_of_all_elements_located(self.MAP_PIN))
            return len(pins) > 0
        except TimeoutException:
            return False
