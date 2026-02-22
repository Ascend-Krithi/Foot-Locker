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
        """
        Test Case 2080:
        1. Launch homepage (URL: https://www.footlocker.com/).
        2. Click 'Find a Store' link.
        3. Verify popup message and 'Select My Store' link.
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.launch_homepage("https://www.footlocker.com/")
        store_locator.click_find_store()
        self.assertTrue(
            store_locator.verify_find_store_popup_message("Choose a preferred store to make shopping easier"),
            "Popup message not found or incorrect."
        )
        self.assertTrue(
            store_locator.verify_select_my_store_link_visible(),
            "'Select My Store' link not visible in popup."
        )

    def test_2079_preferred_store_persistence_across_pages(self):
        """
        Test Case 2079:
        1. Launch homepage, set preferred store to '375 Washington Street, Boston, MA 02108'.
        2. Navigate to MensSneakersPage.
        3. Verify preferred store persists.
        """
        store_locator = StoreLocatorPage(self.driver)
        mens_sneakers = MensSneakersPage(self.driver)
        store_locator.launch_homepage("https://www.footlocker.com/")
        store_locator.click_find_store()
        store_locator.enter_location("Boston, MA")
        store_locator.click_search_for_stores()
        self.assertTrue(
            store_locator.is_store_address_present_in_results("375 Washington Street, Boston, MA 02108"),
            "Store address not present in search results."
        )
        store_locator.click_set_my_store("375 Washington Street, Boston, MA 02108")
        self.assertTrue(
            store_locator.verify_store_address_exact_match("375 Washington Street, Boston, MA 02108"),
            "Preferred store address does not exactly match."
        )
        self.assertTrue(
            store_locator.verify_confirmation(),
            "Confirmation for setting preferred store not found."
        )
        mens_sneakers.launch_page()
        self.assertTrue(
            mens_sneakers.verify_store_indicator("375 Washington Street, Boston, MA 02108"),
            "Preferred store indicator not visible or incorrect on MensSneakersPage."
        )

    def test_2083_invalid_location_shows_no_stores_found(self):
        """
        Test Case 2083: SCRUM-15408 TS-002 TC-002
        1. Launch the Foot Locker website homepage.
        2. Click on the 'Find a Store' link.
        3. Click on the 'Select My Store' link in the popup.
        4. Enter 'InvalidLocation123' in the 'Location' textbox.
        5. Click the 'Search for Stores' button.
        6. Verify a message indicating 'No stores found' is displayed in the popup.
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.launch_homepage("https://www.footlocker.com/")
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.enter_location("InvalidLocation123")
        store_locator.click_search_for_stores()
        self.assertTrue(
            store_locator.verify_find_store_popup_message("No stores found"),
            "'No stores found' message not displayed for invalid location."
        )

    def test_2084_boston_store_address_exact_match(self):
        """
        Test Case 2084: SCRUM-15408 TS-003 TC-001
        1. Launch the Foot Locker website homepage.
        2. Click on the 'Find a Store' link.
        3. Click on the 'Select My Store' link in the popup.
        4. Enter 'Boston, MA' in the 'Location' textbox.
        5. Click the 'Search for Stores' button.
        6. Verify the store with address '375 Washington Street, Boston, MA 02108' is exactly present in the results.
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.launch_homepage("https://www.footlocker.com/")
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.enter_location("Boston, MA")
        store_locator.click_search_for_stores()
        self.assertTrue(
            store_locator.verify_store_address_exact_match("375 Washington Street, Boston, MA 02108"),
            "Exact store address '375 Washington Street, Boston, MA 02108' not found in results."
        )

if __name__ == "__main__":
    unittest.main()
