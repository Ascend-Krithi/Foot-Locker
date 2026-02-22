# Existing imports and code remain unchanged
import unittest
from selenium import webdriver
from PageClasses.StoreSelectionPopup import StoreSelectionPopup
from PageClasses.Confirmation import Confirmation
from PageClasses.Navigation import Navigation

class FootLockerTests(unittest.TestCase):
    # ... (existing test methods remain unchanged)

    def test_2077_set_boston_store_via_store_locator(self):
        """
        Test Case 2077:
        1. Launch the Foot Locker website and navigate to the store locator popup.
        2. Enter 'Boston, MA' in the 'Location' textbox and click 'Search for Stores'.
        3. Locate the store with address '375 Washington Street, Boston, MA 02108' in the results.
        4. Click on 'Set My Store' for the Boston location.
        """
        driver = self.driver
        navigation = Navigation(driver)
        navigation.go_to_homepage()
        store_popup = StoreSelectionPopup(driver)
        store_popup.open_store_locator()
        store_popup.enter_location("Boston, MA")
        store_popup.click_search()
        self.assertTrue(
            store_popup.is_store_in_results("375 Washington Street, Boston, MA 02108"),
            "Boston store should appear in search results."
        )
        store_popup.set_my_store_by_address("375 Washington Street, Boston, MA 02108")

    def test_2078_confirm_boston_store_set_and_persisted(self):
        """
        Test Case 2078:
        1. Launch the Foot Locker website and set the preferred store to '375 Washington Street, Boston, MA 02108' using the store locator popup.
        2. Observe the confirmation indicator after setting the store.
        3. Verify that the selected store appears across the website.
        """
        driver = self.driver
        navigation = Navigation(driver)
        navigation.go_to_homepage()
        store_popup = StoreSelectionPopup(driver)
        store_popup.open_store_locator()
        store_popup.enter_location("Boston, MA")
        store_popup.click_search()
        store_popup.set_my_store_by_address("375 Washington Street, Boston, MA 02108")
        confirmation = Confirmation(driver)
        self.assertTrue(
            confirmation.is_store_set_confirmation_displayed("375 Washington Street, Boston, MA 02108"),
            "Confirmation indicator for Boston store should be displayed."
        )
        navigation.navigate_across_site()
        self.assertTrue(
            navigation.is_selected_store_displayed("375 Washington Street, Boston, MA 02108"),
            "Selected store should be displayed across the website."
        )

# Existing main block and other code remain unchanged
