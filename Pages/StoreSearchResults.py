# StoreSearchResults.py
"""
Page Object for Store Search Results.
Covers search results, setting store, and address validation.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreSearchResults:
    """
    Page Object representing Store Search Results.
    """
    SET_MY_STORE_BUTTON_XPATH_TEMPLATE = "//button[contains(text(), 'Set My Store') and ancestor::div[contains(., '{}')]]"
    STORE_ADDRESS_DIV_XPATH_TEMPLATE = "//div[contains(text(), '{}')]"

    def __init__(self, driver):
        """
        Initializes StoreSearchResults object.
        :param driver: Selenium WebDriver instance
        """
        self.driver = driver

    def is_store_address_visible(self, address, timeout=10):
        """
        Checks if a store address is visible in the search results.
        :param address: Store address string
        :param timeout: Max seconds to wait
        :return: True if address is visible, False otherwise
        """
        xpath = self.STORE_ADDRESS_DIV_XPATH_TEMPLATE.format(address)
        try:
            WebDriverWait(self.driver, timeout).until(
                EC.visibility_of_element_located((By.XPATH, xpath))
            )
            return True
        except Exception:
            return False

    def click_set_my_store_button(self, address, timeout=10):
        """
        Clicks the 'Set My Store' button for a given store address.
        :param address: Store address string
        :param timeout: Max seconds to wait
        :return: None
        """
        xpath = self.SET_MY_STORE_BUTTON_XPATH_TEMPLATE.format(address)
        WebDriverWait(self.driver, timeout).until(
            EC.element_to_be_clickable((By.XPATH, xpath))
        ).click()
