# StoreSearchResults.py
"""
Page Object for Store Search Results.
Handles validation of results and interaction with store entries.
QA Notes:
- Methods allow for result validation and store selection.
- Locators parameterized for flexible address matching.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreSearchResults:
    SET_MY_STORE_BUTTON_XPATH = "//button[contains(text(), 'Set My Store') and ancestor::div[contains(., '{}')]]"
    STORE_ADDRESS_DIV_XPATH = "//div[contains(text(), '{}')]"

    def __init__(self, driver: WebDriver):
        self.driver = driver

    def wait_for_results(self):
        """
        Waits for at least one store result to appear after search.
        Returns True if results are found.
        """
        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_any_elements_located((By.XPATH, "//div[contains(@class, 'store-result') or contains(@class, 'store-address')]")
        ))
        return True

    def verify_store_present(self, address: str):
        """
        Verifies that a store with the specified address is present in results.
        Returns True if found.
        """
        address_xpath = self.STORE_ADDRESS_DIV_XPATH.format(address)
        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((By.XPATH, address_xpath))
        )
        return True

    def set_my_store(self, address: str):
        """
        Clicks the 'Set My Store' button for the store with the given address.
        """
        button_xpath = self.SET_MY_STORE_BUTTON_XPATH.format(address)
        set_store_button = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, button_xpath))
        )
        set_store_button.click()
        return True
