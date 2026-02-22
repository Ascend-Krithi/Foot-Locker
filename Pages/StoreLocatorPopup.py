# StoreLocatorPopup.py
"""
Page Object for the Store Locator Popup
Handles store selection, search actions, and popup validations
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver

class StoreLocatorPopup:
    def __init__(self, driver: WebDriver):
        self.driver = driver

    # Locators
    SELECT_MY_STORE_BUTTON = (By.XPATH, "//button[contains(text(), 'Select My Store')]")
    LOCATION_TEXTBOX = (By.ID, "store-locator-search-input")
    SEARCH_FOR_STORES_BUTTON = (By.XPATH, "//button[contains(text(), 'Search for Stores')]")
    POPUP_MESSAGE = (By.XPATH, "//div[contains(@class, 'store-locator-popup-message')]")

    # Actions
    def click_select_my_store(self):
        self.driver.find_element(*self.SELECT_MY_STORE_BUTTON).click()

    def enter_location(self, location: str):
        textbox = self.driver.find_element(*self.LOCATION_TEXTBOX)
        textbox.clear()
        textbox.send_keys(location)

    def click_search_for_stores(self):
        self.driver.find_element(*self.SEARCH_FOR_STORES_BUTTON).click()

    # Validations
    def is_popup_visible(self):
        return self.driver.find_element(*self.SELECT_MY_STORE_BUTTON).is_displayed()

    def get_popup_message(self):
        return self.driver.find_element(*self.POPUP_MESSAGE).text

    def is_popup_message_visible(self):
        return self.driver.find_element(*self.POPUP_MESSAGE).is_displayed()
