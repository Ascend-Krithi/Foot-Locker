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

    def navigate_to_store_locator(self, url='https://www.footlocker.com/store-locator'):
        self.driver.get(url)
        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['store_locator_page']['by'].upper()),
                self.locators['store_locator_page']['value']
            ))
        )
        return True

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

    def enter_location_and_search(self, location='Boston, MA'):
        location_box = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['location_textbox']['by'].upper()),
                self.locators['location_textbox']['value']
            ))
        )
        location_box.clear()
        location_box.send_keys(location)
        search_button = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((
                getattr(By, self.locators['search_for_stores_button']['by'].upper()),
                self.locators['search_for_stores_button']['value']
            ))
        )
        search_button.click()
        return True

    def locate_boston_store_in_results(self):
        store_result = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['set_my_store_button_boston']['by'].upper()),
                self.locators['set_my_store_button_boston']['value']
            ))
        )
        return store_result.is_displayed()

    def click_set_my_store_boston(self):
        set_my_store_btn = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((
                getattr(By, self.locators['set_my_store_button_boston']['by'].upper()),
                self.locators['set_my_store_button_boston']['value']
            ))
        )
        set_my_store_btn.click()
        return True

    def verify_store_confirmation_indicator(self):
        confirmation = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators['store_confirmation_indicator']['by'].upper()),
                self.locators['store_confirmation_indicator']['value']
            ))
        )
        return confirmation.is_displayed()

    def verify_store_indicator_across_website(self, page_locator_key='store_indicator_on_other_page'):
        indicator = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((
                getattr(By, self.locators[page_locator_key]['by'].upper()),
                self.locators[page_locator_key]['value']
            ))
        )
        return indicator.is_displayed()
