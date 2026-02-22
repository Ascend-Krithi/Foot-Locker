# StoreLocatorPopup.py
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.common.exceptions import NoSuchElementException

class StoreLocatorPopup:
    def __init__(self, driver: WebDriver):
        self.driver = driver

    def is_store_locator_displayed(self):
        try:
            return self.driver.find_element(By.XPATH, "//div[contains(@class, 'store-locator-modal') and @role='dialog']").is_displayed()
        except NoSuchElementException:
            return False

    def enter_location(self, location: str):
        location_textbox = self.driver.find_element(By.ID, "store-locator-search-input")
        location_textbox.clear()
        location_textbox.send_keys(location)

    def click_search_for_stores(self):
        search_button = self.driver.find_element(By.XPATH, "//button[contains(., 'Search for Stores')]")
        search_button.click()

    def is_store_list_displayed(self):
        try:
            # Example: Check for store confirmation indicator for Boston, can be generalized as needed.
            return self.driver.find_element(By.XPATH, "//div[contains(@class, 'preferred-store-confirmation') and contains(., '375 Washington Street')]").is_displayed()
        except NoSuchElementException:
            return False

    def is_error_message_displayed(self):
        try:
            # Example: Check for a generic error message when no stores found
            error_element = self.driver.find_element(By.XPATH, "//div[contains(@class, 'store-locator-error')]")
            return error_element.is_displayed()
        except NoSuchElementException:
            return False
