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

    def verify_store_results(self, expected_address):
        '''
        Verifies that store results contain the expected address (e.g., Boston).
        Returns True if found, False otherwise.
        '''
        try:
            store_elem = WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located((
                    getattr(By, self.locators['set_my_store_button_boston']['by'].upper()),
                    self.locators['set_my_store_button_boston']['value']
                ))
            )
            return expected_address in store_elem.text
        except Exception:
            return False

    def verify_no_stores_found_message(self, expected_message='No stores found near this location'):
        '''
        Verifies that the "no stores found" message is displayed after searching for Nome, Alaska.
        '''
        try:
            popup = WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located((
                    By.XPATH,
                    f"//div[contains(@class, 'store-locator-modal')]//div[contains(text(), '{expected_message}') or contains(text(), 'no stores found')]"
                ))
            )
            return expected_message.lower() in popup.text.lower() or 'no stores found' in popup.text.lower()
        except Exception:
            return False

    def click_set_my_store_boston(self):
        '''
        Clicks the Set My Store button for Boston store.
        '''
        set_btn = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((
                getattr(By, self.locators['set_my_store_button_boston']['by'].upper()),
                self.locators['set_my_store_button_boston']['value']
            ))
        )
        set_btn.click()
        return True

    def verify_store_confirmation_indicator(self, expected_address):
        '''
        Verifies that the confirmation indicator for preferred store is displayed.
        '''
        indicator = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['store_confirmation_indicator']['by'].upper()),
                self.locators['store_confirmation_indicator']['value']
            ))
        )
        return expected_address in indicator.text
