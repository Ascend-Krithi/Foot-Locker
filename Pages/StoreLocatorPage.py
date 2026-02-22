# StoreLocatorPage.py
"""
Page Object for Store Locator Page.
Encapsulates actions for entering a location, searching for stores, and validating results and error messages.
Test Steps Covered:
1. Enter 'Massachusetts' and verify results.
2. Enter 'InvalidCity123' and verify error message.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreLocatorPage:
    """
    Page Object representing the Store Locator Page.
    Provides methods to enter a location, search for stores, and validate results or error messages.
    """
    LOCATION_TEXTBOX = (By.ID, "store-search-location")
    SEARCH_BUTTON = (By.XPATH, "//button[contains(text(), 'Search for Stores')]")
    RESULTS_LIST = (By.ID, "store-results-list")  # Placeholder: Update if needed
    ERROR_MESSAGE = (By.XPATH, "//div[contains(@class, 'store-search-error')]" )  # Placeholder: Update if needed

    def __init__(self, driver):
        """
        Initializes StoreLocatorPage object.
        :param driver: Selenium WebDriver instance
        """
        self.driver = driver

    def enter_location(self, location, timeout=10):
        """
        Enters the specified location in the location textbox.
        :param location: Location string to enter
        :param timeout: Max seconds to wait
        :return: None
        """
        textbox = WebDriverWait(self.driver, timeout).until(
            EC.visibility_of_element_located(self.LOCATION_TEXTBOX)
        )
        textbox.clear()
        textbox.send_keys(location)

    def click_search_button(self, timeout=10):
        """
        Clicks the 'Search for Stores' button.
        :param timeout: Max seconds to wait
        :return: None
        """
        WebDriverWait(self.driver, timeout).until(
            EC.element_to_be_clickable(self.SEARCH_BUTTON)
        ).click()

    def is_results_list_displayed(self, timeout=10):
        """
        Validates that the results list is displayed after searching for a valid location.
        :param timeout: Max seconds to wait
        :return: True if results list is visible, False otherwise
        """
        try:
            WebDriverWait(self.driver, timeout).until(
                EC.visibility_of_element_located(self.RESULTS_LIST)
            )
            return True
        except Exception:
            return False

    def is_error_message_displayed(self, timeout=10):
        """
        Validates that the error message is displayed for invalid location input.
        :param timeout: Max seconds to wait
        :return: True if error message is visible, False otherwise
        """
        try:
            WebDriverWait(self.driver, timeout).until(
                EC.visibility_of_element_located(self.ERROR_MESSAGE)
            )
            return True
        except Exception:
            return False
