# ConfirmationPage.py
"""
Page Object for Confirmation Page
Handles confirmation indicators after store selection
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver

class ConfirmationPage:
    def __init__(self, driver: WebDriver):
        self.driver = driver

    CONFIRMATION_INDICATOR = (By.XPATH, "//div[contains(@class, 'confirmation') and contains(text(), 'Your store has been set')]")

    def is_store_set_confirmation_visible(self):
        return self.driver.find_element(*self.CONFIRMATION_INDICATOR).is_displayed()
