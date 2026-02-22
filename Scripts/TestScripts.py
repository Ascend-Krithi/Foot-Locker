# Existing imports and test methods remain unchanged
import unittest
from selenium import webdriver
from PageClasses.HomePage import HomePage
from PageClasses.StoreLocatorPopup import StoreLocatorPopup

class TestScripts(unittest.TestCase):
    # ... (existing test methods) ...

    def test_set_preferred_store_and_persistence_2079(self):
        """
        Test Case 2079: Set preferred store to '375 Washington Street, Boston, MA 02108',
        navigate to product listing, and verify preferred store persists.
        """
        driver = webdriver.Chrome()
        try:
            homepage = HomePage(driver)
            homepage.load()
            homepage.open_store_locator()
            store_popup = StoreLocatorPopup(driver)
            store_popup.search_store("375 Washington Street, Boston, MA 02108")
            store_popup.select_store("375 Washington Street, Boston, MA 02108")
            store_popup.set_as_preferred_store()
            homepage.navigate_to_product_listing()
            persisted_store = homepage.get_preferred_store()
            self.assertEqual(persisted_store, "375 Washington Street, Boston, MA 02108", "Preferred store did not persist.")
        finally:
            driver.quit()

    def test_homepage_find_store_popup_2080(self):
        """
        Test Case 2080: Homepage loads, click 'Find a Store', verify popup and message.
        """
        driver = webdriver.Chrome()
        try:
            homepage = HomePage(driver)
            homepage.load()
            homepage.open_store_locator()
            store_popup = StoreLocatorPopup(driver)
            self.assertTrue(store_popup.is_displayed(), "Store Locator popup did not display.")
            expected_message = "Find a store near you"
            actual_message = store_popup.get_popup_message()
            self.assertIn(expected_message, actual_message, f"Popup message mismatch. Expected '{expected_message}' in '{actual_message}'")
        finally:
            driver.quit()
