
import unittest
from selenium import webdriver
from Pages.StoreLocatorPage import StoreLocatorPage

class TestScripts(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome()
        self.locators = {}  # Assume this is populated appropriately

    def tearDown(self):
        self.driver.quit()

    # Existing test methods (unchanged)
    # ... [Assume all previous methods are here] ...

    # --- New test for TC 2081 ---
    def test_2081_store_locator_popup_elements(self):
        """TC2081: Verify Store Locator popup elements"""
        store_locator = StoreLocatorPage(self.driver, self.locators)
        store_locator.launch_homepage()
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.verify_location_textbox_and_search_button_present()

    # --- New test for TC 2082 ---
    def test_2082_store_locator_search_results(self):
        """TC2082: Verify search results for location input"""
        store_locator = StoreLocatorPage(self.driver, self.locators)
        store_locator.launch_homepage()
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.enter_location('Boston, MA')
        store_locator.click_search_for_stores()
        store_locator.verify_search_results_present('Boston, MA')
