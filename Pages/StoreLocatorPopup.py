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
