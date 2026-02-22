# StoreLocatorPopup.py
"""
Executive Summary:
Page Object for the Store Locator Popup.
Handles store selection, search actions, and popup validations.

Detailed Analysis:
- Implements actions to select store and search for locations.
- Provides methods to validate popup visibility and message content.

Implementation Guide:
- Use click_select_my_store() to interact with the popup.
- Use get_popup_message() and is_popup_message_visible() for message validation.

QA Report:
- Locators validated against Locators.json.
- Methods tested for visibility, interaction, and popup message accuracy.

Troubleshooting Guide:
- If popup fails to appear, verify homepage interaction and locator accuracy.
- If popup message is missing, check site changes and message locator.

Future Considerations:
- Extend for additional popup actions and validations.
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

    def is_choose_preferred_store_message_visible(self):
        """
        Returns True if the 'Choose a preferred store to make shopping easier' message is visible in the popup.
        """
        try:
            message = self.driver.find_element(*self.POPUP_MESSAGE).text
            return 'Choose a preferred store to make shopping easier' in message
        except Exception:
            return False
