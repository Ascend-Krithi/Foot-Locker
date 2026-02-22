# Existing imports and code assumed from previous content
import unittest
from selenium import webdriver
from Pages.StoreLocatorPage import StoreLocatorPage
import json

class TestStoreLocator(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        with open('Locators.json') as f:
            cls.locators = json.load(f)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    # Existing test methods remain unchanged

    def test_2093_verify_location_textbox_present_and_enabled(self):
        """
        Test Case 2093:
        - Launch homepage
        - Navigate to Find a Store
        - Open Store Locator Popup
        - Verify location textbox is present and enabled
        """
        store_locator = StoreLocatorPage(self.driver, self.locators)
        store_locator.launch_homepage()
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        self.assertTrue(
            store_locator.is_location_textbox_enabled(),
            "Location textbox should be present and enabled."
        )

    def test_2094_search_for_boston_and_verify_results(self):
        """
        Test Case 2094:
        - Launch homepage
        - Navigate to Find a Store
        - Open Store Locator Popup
        - Enter 'Boston, MA' in location textbox
        - Click search
        - Verify stores are displayed for Boston
        """
        store_locator = StoreLocatorPage(self.driver, self.locators)
        store_locator.launch_homepage()
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.enter_location('Boston, MA')
        store_locator.click_search_for_stores()
        self.assertTrue(
            store_locator.verify_store_results('Boston'),
            "Store results for 'Boston' should be displayed."
        )

# Existing code below (if any) remains unchanged
