# Existing imports and code remain unchanged
import unittest
from selenium import webdriver
from PageClasses.StoreSelectionPopup import StoreSelectionPopup
from PageClasses.Confirmation import Confirmation
from PageClasses.Navigation import Navigation

class FootLockerTests(unittest.TestCase):
    # ... (existing test methods remain unchanged)

    def test_2077_set_boston_store_via_store_locator(self):
        ...

    def test_2078_confirm_boston_store_set_and_persisted(self):
        ...

    def test_2081_open_store_locator_and_verify_elements(self):
        """
        Test Case 2081:
        1. Launch homepage.
        2. Click 'Find a Store'.
        3. Click 'Select My Store' in popup.
        Verify popups and elements as per steps.
        """
        driver = self.driver if hasattr(self, 'driver') else webdriver.Chrome()
        try:
            from PageClasses.HomePage import HomePage
            from PageClasses.StoreLocatorPopup import StoreLocatorPopup
            from PageClasses.StoreSelectionPopup import StoreSelectionPopup

            homepage = HomePage(driver)
            homepage.load_homepage()

            homepage.click_find_a_store()
            store_locator_popup = StoreLocatorPopup(driver)

            # Wait for popup to be visible
            self.assertTrue(store_locator_popup.is_popup_visible(), "Store Locator popup should be visible after clicking 'Find a Store'.")

            # Optionally, verify popup message
            popup_message = store_locator_popup.get_popup_message()
            self.assertIsNotNone(popup_message)

            # Verify 'Select My Store' button is visible
            self.assertTrue(store_locator_popup.is_select_my_store_button_visible(), "'Select My Store' button should be visible in the popup.")

            # Click 'Select My Store'
            store_locator_popup.click_select_my_store_button()
            store_selection_popup = StoreSelectionPopup(driver)

            # Verify Store Selection Popup elements
            self.assertTrue(store_selection_popup.is_location_textbox_present(), "Location textbox should be present in Store Selection popup.")
            self.assertTrue(store_selection_popup.is_search_button_present(), "Search button should be present in Store Selection popup.")
        finally:
            if not hasattr(self, 'driver'):
                driver.quit()

    def test_2082_search_store_by_location(self):
        """
        Test Case 2082:
        1. Launch homepage.
        2. Click 'Find a Store'.
        3. Click 'Select My Store'.
        4. Enter 'Boston, MA'.
        5. Click 'Search for Stores'.
        Verify each step as per test case.
        """
        driver = self.driver if hasattr(self, 'driver') else webdriver.Chrome()
        try:
            from PageClasses.HomePage import HomePage
            from PageClasses.StoreLocatorPopup import StoreLocatorPopup
            from PageClasses.StoreSelectionPopup import StoreSelectionPopup

            homepage = HomePage(driver)
            homepage.load_homepage()

            homepage.click_find_a_store()
            store_locator_popup = StoreLocatorPopup(driver)
            self.assertTrue(store_locator_popup.is_popup_visible(), "Store Locator popup should be visible after clicking 'Find a Store'.")
            self.assertTrue(store_locator_popup.is_select_my_store_button_visible(), "'Select My Store' button should be visible in the popup.")

            store_locator_popup.click_select_my_store_button()
            store_selection_popup = StoreSelectionPopup(driver)
            self.assertTrue(store_selection_popup.is_location_textbox_present(), "Location textbox should be present in Store Selection popup.")
            self.assertTrue(store_selection_popup.is_search_button_present(), "Search button should be present in Store Selection popup.")

            # Enter location and search
            store_selection_popup.enter_location("Boston, MA")
            store_selection_popup.click_search_for_stores()
            # Further verification of results can be added here as per requirements
        finally:
            if not hasattr(self, 'driver'):
                driver.quit()

# Existing main block and other code remain unchanged
