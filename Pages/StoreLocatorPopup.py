# imports
import json
import os
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreLocatorPopup:
    def __init__(self, driver):
        self.driver = driver
        with open(os.path.join(os.path.dirname(__file__), '../Locators/Locators.json')) as f:
            self.locators = json.load(f)

    def wait_for_popup(self):
        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['store_locator_popup']['by'].upper()),
                self.locators['store_locator_popup']['value']
            ))
        )
        return True

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

    def are_store_results_displayed(self):
        # This method checks if at least one store result is visible after search
        try:
            store_results = WebDriverWait(self.driver, 10).until(
                EC.visibility_of_any_elements_located((
                    By.XPATH,
                    "//div[contains(@class, 'store-result')]"
                ))
            )
            return len(store_results) > 0
        except Exception:
            return False

    def is_store_address_present_in_results(self, address):
        # This method checks if a store with the given address is present in the search results
        try:
            store_results = WebDriverWait(self.driver, 10).until(
                EC.visibility_of_any_elements_located((
                    By.XPATH,
                    "//div[contains(@class, 'store-result')]"
                ))
            )
            for store in store_results:
                if address in store.text:
                    return True
            return False
        except Exception:
            return False
