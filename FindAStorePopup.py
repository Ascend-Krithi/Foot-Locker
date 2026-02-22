# FindAStorePopup.py
"""
Page Object for the Store Locator Popup.
Handles entering location and triggering search.
QA Notes:
- Locators and methods are strictly based on test steps and provided locators.
- Ensures popup visibility and robust element interaction.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class FindAStorePopup:
    POPUP_CONTAINER = (By.ID, "find-store-popup")
    SELECT_MY_STORE_LINK = (By.LINK_TEXT, "Select My Store")
    LOCATION_TEXTBOX = (By.ID, "store-search-location")
    SEARCH_BUTTON = (By.XPATH, "//button[contains(text(), 'Search for Stores')]")

    def __init__(self, driver: WebDriver):
        self.driver = driver

    def wait_for_popup(self):
        """
        Waits for the Store Locator popup to be visible.
        Returns True if visible.
        """
        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.POPUP_CONTAINER)
        )
        return True

    def enter_location(self, location: str):
        """
        Enters the given location into the location textbox.
        """
        location_box = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.LOCATION_TEXTBOX)
        )
        location_box.clear()
        location_box.send_keys(location)
        return True

    def click_search(self):
        """
        Clicks the 'Search for Stores' button.
        """
        search_button = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.SEARCH_BUTTON)
        )
        search_button.click()
        return True
