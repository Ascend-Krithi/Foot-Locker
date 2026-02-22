import unittest
from selenium import webdriver
from Pages.StoreLocatorPage import StoreLocatorPage
from Pages.MensSneakersPage import MensSneakersPage

class TestScripts(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.driver.implicitly_wait(10)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    # Existing test methods remain here...

    def test_2077_store_locator_select_and_verify_preferred_store(self):
        ...

    def test_2078_verify_selected_store_visible_across_website(self):
        ...

    def test_2080_find_store_popup_verification(self):
        ...

    def test_2079_preferred_store_persistence_across_pages(self):
        ...

    def test_2083_invalid_location_shows_no_stores_found(self):
        ...

    def test_2084_boston_store_address_exact_match(self):
        ...

    def test_2085_store_locator_address_format(self):
        """
        Test Case 2085: SCRUM-15408 TS-003 TC-002
        1. Launch the Foot Locker website homepage (https://www.footlocker.com/).
        2. Click on the 'Find a Store' link.
        3. Click on the 'Select My Store' link.
        4. Enter 'Boston, MA' in the 'Location' textbox.
        5. Click the 'Search for Stores' button.
        6. Locate the store with address '375 Washington Street, Boston, MA 02108' in the results.
        7. Verify the address format matches '375 Washington Street, Boston, MA 02108'.
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.launch_homepage("https://www.footlocker.com/")
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.enter_location("Boston, MA")
        store_locator.click_search_for_stores()
        self.assertTrue(
            store_locator.is_store_address_present_in_results("375 Washington Street, Boston, MA 02108"),
            "Store address '375 Washington Street, Boston, MA 02108' not present in search results."
        )
        self.assertTrue(
            store_locator.verify_store_address_exact_match("375 Washington Street, Boston, MA 02108"),
            "Store address does not exactly match '375 Washington Street, Boston, MA 02108'."
        )

    def test_2086_set_preferred_store(self):
        """
        Test Case 2086: SCRUM-15408 TS-004 TC-001
        1. Launch the Foot Locker website homepage (https://www.footlocker.com/).
        2. Click on the 'Find a Store' link.
        3. Click on the 'Select My Store' link.
        4. Enter 'Boston, MA' in the 'Location' textbox.
        5. Click the 'Search for Stores' button.
        6. Locate the store with address '375 Washington Street, Boston, MA 02108' in the results.
        7. Click the 'Set My Store' button for this store and verify confirmation.
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.launch_homepage("https://www.footlocker.com/")
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.enter_location("Boston, MA")
        store_locator.click_search_for_stores()
        self.assertTrue(
            store_locator.is_store_address_present_in_results("375 Washington Street, Boston, MA 02108"),
            "Store address '375 Washington Street, Boston, MA 02108' not present in search results."
        )
        store_locator.click_set_my_store()
        self.assertTrue(
            store_locator.verify_confirmation(),
            "Confirmation for setting preferred store not found."
        )

if __name__ == "__main__":
    unittest.main()
