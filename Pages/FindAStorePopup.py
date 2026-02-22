# FindAStorePopup.py
"""
Page Object for Store Locator Popup.
Covers popup display, message, links, and search functionality.
Test Steps Covered:
- Verify Store Locator popup appears after clicking 'Find a Store'.
- Verify popup message and 'Select My Store' link.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class FindAStorePopup:
    """
    Page Object representing the Store Locator Popup.
    """
    POPUP_CONTAINER = (By.ID, "find-store-popup")
    SELECT_MY_STORE_LINK = (By.LINK_TEXT, "Select My Store")
    LOCATION_TEXTBOX = (By.ID, "store-search-location")
    SEARCH_BUTTON = (By.XPATH, "//button[contains(text(), 'Search for Stores')]")
    POPUP_MESSAGE_XPATH = (By.XPATH, "//div[contains(text(), 'Choose a preferred store to make shopping easier')]")

    def __init__(self, driver):
        """
        Initializes FindAStorePopup object.
        :param driver: Selenium WebDriver instance
        """
        self.driver = driver

    def is_popup_displayed(self, timeout=10):
        """
        Verifies that the Store Locator popup is visible.
        :param timeout: Max seconds to wait
        :return: True if displayed, False otherwise
        """
        try:
            WebDriverWait(self.driver, timeout).until(
                EC.visibility_of_element_located(self.POPUP_CONTAINER)
            )
            return True
        except Exception:
            return False

    def is_popup_message_displayed(self, timeout=10):
        """
        Checks if the popup message 'Choose a preferred store to make shopping easier' is displayed.
        :param timeout: Max seconds to wait
        :return: True if message is visible, False otherwise
        """
        try:
            WebDriverWait(self.driver, timeout).until(
                EC.visibility_of_element_located(self.POPUP_MESSAGE_XPATH)
            )
            return True
        except Exception:
            return False

    def is_select_my_store_link_visible(self, timeout=10):
        """
        Checks if the 'Select My Store' link is visible in the popup.
        :param timeout: Max seconds to wait
        :return: True if link is visible, False otherwise
        """
        try:
            WebDriverWait(self.driver, timeout).until(
                EC.visibility_of_element_located(self.SELECT_MY_STORE_LINK)
            )
            return True
        except Exception:
            return False

    def click_select_my_store_link(self, timeout=10):
        """
        Clicks the 'Select My Store' link in the popup.
        :param timeout: Max seconds to wait
        :return: None
        """
        WebDriverWait(self.driver, timeout).until(
            EC.element_to_be_clickable(self.SELECT_MY_STORE_LINK)
        ).click()
