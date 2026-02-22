# StoreSelectionPopup.py
"""
Page Object for Store Selection Popup
Handles store selection from search results
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver

class StoreSelectionPopup:
    def __init__(self, driver: WebDriver):
        self.driver = driver

    SET_MY_STORE_BUTTON = (By.XPATH, "//div[contains(text(), '375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class, 'store-card')]//button[contains(text(), 'Set My Store')]")

    def set_my_store(self):
        self.driver.find_element(*self.SET_MY_STORE_BUTTON).click()

    def is_store_visible(self):
        return self.driver.find_element(*self.SET_MY_STORE_BUTTON).is_displayed()
