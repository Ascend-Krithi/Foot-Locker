# StoreSearchResults.py
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreSearchResults:
    SET_MY_STORE_BUTTON_XPATH = "//button[contains(text(), 'Set My Store') and ancestor::div[contains(., '{address}')]]"
    STORE_ADDRESS_DIV_XPATH = "//div[contains(text(), '{address}')"]

    def __init__(self, driver: WebDriver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 10)

    def set_my_store_by_address(self, address: str):
        set_my_store_xpath = self.SET_MY_STORE_BUTTON_XPATH.format(address=address)
        set_my_store_btn = self.wait.until(
            EC.element_to_be_clickable((By.XPATH, set_my_store_xpath))
        )
        set_my_store_btn.click()

    def is_store_address_present(self, address: str) -> bool:
        address_xpath = self.STORE_ADDRESS_DIV_XPATH.format(address=address)
        try:
            self.wait.until(
                EC.visibility_of_element_located((By.XPATH, address_xpath))
            )
            return True
        except:
            return False

    def is_store_set_confirmation_displayed(self, address: str) -> bool:
        # This method attempts to check for confirmation indicator after setting store
        # (e.g., highlight, confirmation message, or header update).
        # This implementation assumes a confirmation appears in a div near the address.
        # Adjust selector as needed for actual UI.
        address_xpath = self.STORE_ADDRESS_DIV_XPATH.format(address=address)
        confirmation_xpath = address_xpath + "/following-sibling::div[contains(@class, 'confirmation') or contains(text(), 'preferred') or contains(text(), 'set as your store')]"
        try:
            self.wait.until(
                EC.visibility_of_element_located((By.XPATH, confirmation_xpath))
            )
            return True
        except:
            return False
