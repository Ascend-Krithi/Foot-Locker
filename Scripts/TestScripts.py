# Existing imports
from selenium.webdriver.common.by import By
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup
import unittest

class TestScripts(unittest.TestCase):
    # ...existing test methods...

    def test_2081_verify_store_locator_popup_fields(self):
        """
        Test Case 2081:
        1. Load homepage
        2. Click Find a Store
        3. Click Select My Store
        4. Verify popup fields
        """
        driver = self.driver
        home_page = HomePage(driver)
        store_popup = StoreLocatorPopup(driver)
        
        # Step 1: Load homepage
        home_page.load()
        
        # Step 2: Click Find a Store
        home_page.click_find_a_store()
        
        # Step 3: Click Select My Store
        home_page.click_select_my_store()
        
        # Step 4: Verify popup fields
        self.assertTrue(store_popup.is_popup_displayed(), "Store locator popup not displayed.")
        self.assertTrue(store_popup.is_search_field_present(), "Search field not present in popup.")
        self.assertTrue(store_popup.is_search_button_present(), "Search button not present in popup.")
        self.assertTrue(store_popup.is_close_button_present(), "Close button not present in popup.")

    def test_2082_search_store_by_location(self):
        """
        Test Case 2082:
        1. Load homepage
        2. Click Find a Store
        3. Click Select My Store
        4. Enter 'Boston, MA'
        5. Click Search for Stores
        6. Verify results
        """
        driver = self.driver
        home_page = HomePage(driver)
        store_popup = StoreLocatorPopup(driver)
        
        # Step 1: Load homepage
        home_page.load()
        
        # Step 2: Click Find a Store
        home_page.click_find_a_store()
        
        # Step 3: Click Select My Store
        home_page.click_select_my_store()
        
        # Step 4: Enter 'Boston, MA'
        store_popup.enter_search_text('Boston, MA')
        
        # Step 5: Click Search for Stores
        store_popup.click_search_button()
        
        # Step 6: Verify results
        self.assertTrue(store_popup.is_results_displayed(), "Search results not displayed for 'Boston, MA'.")
