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
        """Test Case 2077: Select a store as preferred and verify indicator"""
        store_locator = StoreLocatorPage(self.driver)
        # Step 1: Launch the homepage
        store_locator.launch_homepage()
        # Step 2: Open the store locator
        store_locator.click_find_store()
        # Step 3: Enter location
        test_location = "12345"
        store_locator.enter_location(test_location)
        # Step 4: Search for stores
        store_locator.click_search_for_stores()
        # Step 5: Verify the specific store is in results
        expected_store = "SneakerTown"
        self.assertTrue(store_locator.is_store_in_results(expected_store), f"Store '{expected_store}' not found in results")
        # Step 6: Verify store address exact match
        expected_address = "123 Main St, Anytown, NY 12345"
        self.assertTrue(store_locator.verify_store_address_exact_match(expected_store, expected_address), f"Address for '{expected_store}' does not match '{expected_address}'")
        # Step 7: Set as preferred store
        store_locator.click_set_my_store(expected_store)
        # Step 8: Confirm the indicator is shown
        self.assertTrue(store_locator.verify_confirmation(expected_store), f"Preferred store confirmation not shown for '{expected_store}'")

    def test_2078_verify_selected_store_visible_across_website(self):
        """Test Case 2078: Verify preferred store selection is visible across the website"""
        store_locator = StoreLocatorPage(self.driver)
        mens_sneakers = MensSneakersPage(self.driver)
        # Step 1: Launch the homepage
        store_locator.launch_homepage()
        # Step 2: Open the store locator and set preferred store
        store_locator.click_find_store()
        test_location = "12345"
        store_locator.enter_location(test_location)
        store_locator.click_search_for_stores()
        expected_store = "SneakerTown"
        store_locator.click_set_my_store(expected_store)
        # Step 3: Navigate to Men's Sneakers page
        mens_sneakers.go_to_page()
        # Step 4: Verify selected store is visible across the website
        self.assertTrue(store_locator.verify_selected_store_visible_across_website(expected_store), f"Preferred store '{expected_store}' not visible across website")

if __name__ == "__main__":
    unittest.main()
