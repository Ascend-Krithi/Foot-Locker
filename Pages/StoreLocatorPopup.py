import json
import os
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreLocatorPopup:
    def __init__(self, driver):
        self.driver = driver
        with open(os.path.join(os.path.dirname(__file__), '../Locators.json')) as f:
            self.locators = json.load(f)

    def wait_for_popup(self):
        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['store_locator_popup']['by'].upper()),
                self.locators['store_locator_popup']['value']
            ))
        )
        return True

    def verify_popup_message(self, expected_message='Choose a preferred store to make shopping easier'):
        popup = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['store_locator_popup']['by'].upper()),
                self.locators['store_locator_popup']['value']
            ))
        )
        return expected_message in popup.text

    def is_select_my_store_link_visible(self):
        select_my_store = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['select_my_store_button']['by'].upper()),
                self.locators['select_my_store_button']['value']
            ))
        )
        return select_my_store.is_displayed()

    def click_select_my_store(self):
        select_my_store = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((
                getattr(By, self.locators['select_my_store_button']['by'].upper()),
                self.locators['select_my_store_button']['value']
            ))
        )
        select_my_store.click()
        return True

    def verify_location_textbox_and_search_button(self):
        location_box = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['location_textbox']['by'].upper()),
                self.locators['location_textbox']['value']
            ))
        )
        search_button = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['search_for_stores_button']['by'].upper()),
                self.locators['search_for_stores_button']['value']
            ))
        )
        return location_box.is_displayed() and search_button.is_displayed()

    def enter_location(self, location):
        location_box = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['location_textbox']['by'].upper()),
                self.locators['location_textbox']['value']
            ))
        )
        location_box.clear()
        location_box.send_keys(location)
        return location_box.get_attribute('value') == location

    def click_search_for_stores(self):
        search_button = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((
                getattr(By, self.locators['search_for_stores_button']['by'].upper()),
                self.locators['search_for_stores_button']['value']
            ))
        )
        search_button.click()
        return True

    # --- New methods appended for SCRUM-15408 TS-003 TC-002 and TS-004 TC-001 ---
    def select_store_by_address(self, address):
        """
        Selects a store result by its address.
        Args:
            address (str): Store address to match (e.g., '375 Washington Street, Boston, MA 02108').
        Returns:
            bool: True if store is found and selected, False otherwise.
        Note: Locator is a placeholder and must be updated in Locators.json.
        """
        # Placeholder locator key: 'store_address_results'
        stores = WebDriverWait(self.driver, 10).until(
            EC.presence_of_all_elements_located((
                getattr(By, self.locators.get('store_address_results', {'by':'XPATH'})['by'].upper()),
                self.locators.get('store_address_results', {'value':'//div[@class="store-address"]'})['value']
            ))
        )
        for store in stores:
            if address in store.text:
                store.click()
                return True
        return False

    def verify_store_address_format(self, address):
        """
        Verifies the address format of the selected store.
        Args:
            address (str): Expected address format.
        Returns:
            bool: True if address format is correct, False otherwise.
        Note: Locator is a placeholder and must be updated in Locators.json.
        """
        # Placeholder locator key: 'selected_store_address'
        addr_elem = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators.get('selected_store_address', {'by':'XPATH'})['by'].upper()),
                self.locators.get('selected_store_address', {'value':'//div[@class="selected-store-address"]'})['value']
            ))
        )
        return address in addr_elem.text

    def click_set_my_store(self):
        """
        Clicks the 'Set My Store' button for the selected store.
        Returns:
            bool: True if click is successful.
        Note: Locator is a placeholder and must be updated in Locators.json.
        """
        # Placeholder locator key: 'set_my_store_button'
        set_btn = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((
                getattr(By, self.locators.get('set_my_store_button', {'by':'XPATH'})['by'].upper()),
                self.locators.get('set_my_store_button', {'value':'//button[contains(text(),"Set My Store")]'})['value']
            ))
        )
        set_btn.click()
        return True

    def verify_preferred_store_saved(self, address):
        """
        Verifies that the preferred store is saved and displayed in the UI.
        Args:
            address (str): Expected preferred store address.
        Returns:
            bool: True if preferred store is displayed, False otherwise.
        Note: Locator is a placeholder and must be updated in Locators.json.
        """
        # Placeholder locator key: 'preferred_store_display'
        pref_store = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators.get('preferred_store_display', {'by':'XPATH'})['by'].upper()),
                self.locators.get('preferred_store_display', {'value':'//div[@class="preferred-store"]'})['value']
            ))
        )
        return address in pref_store.text
