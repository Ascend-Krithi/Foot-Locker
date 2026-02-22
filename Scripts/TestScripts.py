# Existing imports and code remain unchanged
import unittest
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup

class TestScripts(unittest.TestCase):
    # ... (existing test methods remain unchanged)

    def test_enter_zipcode_02108_and_verify_boston_store_results(self):
        """
        Test Case 2095: Enter ZIP code '02108' and verify store results for Boston are displayed.
        """
        homepage = HomePage(self.driver)
        homepage.load_homepage()
        homepage.click_find_a_store()
        store_popup = StoreLocatorPopup(self.driver)
        store_popup.click_select_my_store()
        store_popup.enter_location('02108')
        store_popup.click_search_for_stores()
        # Verify Boston stores are displayed
        self.assertTrue(store_popup.verify_store_results('Boston'), "Boston store results not displayed for ZIP 02108.")

    def test_enter_nome_alaska_and_verify_no_stores_found(self):
        """
        Test Case 2096: Enter 'Nome, Alaska' and verify 'No stores found' message is displayed.
        """
        homepage = HomePage(self.driver)
        homepage.load_homepage()
        homepage.click_find_a_store()
        store_popup = StoreLocatorPopup(self.driver)
        store_popup.click_select_my_store()
        store_popup.enter_location('Nome, Alaska')
        store_popup.click_search_for_stores()
        # Verify 'No stores found' message is displayed
        self.assertTrue(store_popup.verify_no_stores_found_message(), "'No stores found' message not displayed for Nome, Alaska.")
