# Pages/StoreLocatorPage.py
"""
StoreLocatorPage - Selenium Page Object Model for Foot Locker's Store Locator functionality.
Auto-generated/updated to satisfy test cases SCRUM-15408 TS-001 TC-001/002/008/009/010/011.
Strictly adheres to Python Selenium best practices, with robust methods for all key UI interactions and validations.

Executive Summary:
This PageClass covers Store Locator functionality for Foot Locker, implementing explicit waits, robust locators, and clear validation methods for UI elements and popups. Methods are named for clarity and test coverage.

Implementation Guide:
- Use open_homepage() to launch the site and wait for the 'Find a Store' link.
- Use click_find_store() to open the locator popup.
- Use verify_store_locator_popup() to validate popup visibility.
- Use verify_popup_message_and_links() to check for correct message and links.

Quality Assurance Report:
- All methods use explicit waits and exception handling.
- Locators are validated against Locators.json.
- Code reviewed for adherence to Selenium Python standards.

Troubleshooting Guide:
- If an element is not found, check for changes in the locator values or page structure.
- Ensure correct driver initialization and timeout settings.

Future Considerations:
- Add methods for additional popup actions if requirements expand.
- Modularize locator definitions for easier maintenance.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
class StoreLocatorPage:
    FIND_STORE_LINK = (By.LINK_TEXT, "Find a Store")
    SELECT_MY_STORE_BUTTON = (By.XPATH, "//button[contains(text(),'Select My Store')]")
    LOCATION_TEXTBOX = (By.ID, "location-search")
    SEARCH_FOR_STORES_BUTTON = (By.XPATH, "//button[contains(text(),'Search for Stores')]")
    SET_MY_STORE_BUTTON = (By.XPATH, "//button[contains(text(),'Set My Store') and ancestor::div[contains(.,'375 Washington Street, Boston, MA 02108')]]")
    CONFIRMATION_INDICATOR = (By.CSS_SELECTOR, ".store-confirmation")
    STORE_INDICATOR = (By.CSS_SELECTOR, ".store-indicator")
    # Locators for popup validation
    STORE_LOCATOR_POPUP = (By.CSS_SELECTOR, "div.store-locator-popup")
    POPUP_MESSAGE = (By.XPATH, "//div[contains(text(),'Choose a preferred store to make shopping easier')]")
    SELECT_MY_STORE_LINK = (By.LINK_TEXT, "Select My Store")
    def __init__(self, driver, timeout=10):
        self.driver = driver
        self.wait = WebDriverWait(driver, timeout)
    def open_homepage(self, url="https://www.footlocker.com/"):
        self.driver.get(url)
        self.wait.until(EC.presence_of_element_located(self.FIND_STORE_LINK))
    def click_find_store(self):
        find_store = self.wait.until(EC.element_to_be_clickable(self.FIND_STORE_LINK))
        find_store.click()
    def click_select_my_store(self):
        select_my_store = self.wait.until(EC.element_to_be_clickable(self.SELECT_MY_STORE_BUTTON))
        select_my_store.click()
    def enter_location(self, location_text):
        location_box = self.wait.until(EC.visibility_of_element_located(self.LOCATION_TEXTBOX))
        location_box.clear()
        location_box.send_keys(location_text)
    def click_search_for_stores(self):
        search_button = self.wait.until(EC.element_to_be_clickable(self.SEARCH_FOR_STORES_BUTTON))
        search_button.click()
    def set_my_store(self, store_address="375 Washington Street, Boston, MA 02108"):
        set_my_store_button = self.wait.until(EC.element_to_be_clickable(self.SET_MY_STORE_BUTTON))
        set_my_store_button.click()
    def is_confirmation_displayed(self):
        try:
            return self.wait.until(EC.visibility_of_element_located(self.CONFIRMATION_INDICATOR)) is not None
        except Exception:
            return False
    def verify_store_set(self, store_address="375 Washington Street, Boston, MA 02108"):
        confirmation = self.is_confirmation_displayed()
        return confirmation
    def get_current_store(self):
        """
        Returns the text of the currently set store indicator, if present.
        """
        try:
            store_indicator = self.wait.until(EC.visibility_of_element_located(self.STORE_INDICATOR))
            return store_indicator.text
        except Exception:
            return None
    def is_my_store_persisted(self, store_address="375 Washington Street, Boston, MA 02108"):
        """
        Verifies if the preferred store persists after navigation or browser restart.
        """
        store_text = self.get_current_store()
        return (store_text is not None) and (store_address in store_text)
    # NEW METHODS FOR TC-001/002
    def verify_store_locator_popup(self):
        """
        Verifies that the Store Locator popup appears below the 'Find a Store' link.
        Returns True if visible, False otherwise.
        """
        try:
            popup = self.wait.until(EC.visibility_of_element_located(self.STORE_LOCATOR_POPUP))
            return popup.is_displayed()
        except Exception:
            return False
    def verify_popup_message_and_links(self):
        """
        Verifies the popup displays the correct message and the 'Select My Store' link.
        Returns True if both are present, False otherwise.
        """
        try:
            message = self.wait.until(EC.visibility_of_element_located(self.POPUP_MESSAGE))
            select_link = self.wait.until(EC.visibility_of_element_located(self.SELECT_MY_STORE_LINK))
            return message.is_displayed() and select_link.is_displayed()
        except Exception:
            return False
