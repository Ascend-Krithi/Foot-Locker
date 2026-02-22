import unittest
from selenium import webdriver
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup

class TestFootLocker(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.driver.maximize_window()
        cls.home_page = HomePage(cls.driver)
        cls.store_locator_popup = StoreLocatorPopup(cls.driver)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    # Existing test methods...

    def test_2107_store_locator_massachusetts(self):
        """Test Case 2107: Launch Foot Locker website, navigate to Store Locator, enter 'Massachusetts', click 'Search for Stores', and verify store list is displayed."""
        self.home_page.load_homepage()
        self.home_page.click_find_a_store()
        self.store_locator_popup.wait_for_popup()
        self.assertTrue(self.store_locator_popup.verify_location_textbox_and_search_button(), "Location textbox and search button should be visible.")
        self.assertTrue(self.store_locator_popup.enter_location('Massachusetts'), "Should be able to enter 'Massachusetts'.")
        self.assertTrue(self.store_locator_popup.click_search_for_stores(), "Should be able to click search for stores.")
        self.assertTrue(self.store_locator_popup.are_store_results_displayed(), "Store list should be displayed for valid location 'Massachusetts'.")

    def test_2108_store_locator_invalid_city(self):
        """Test Case 2108: Launch Foot Locker website, navigate to Store Locator, enter 'InvalidCity123', click 'Search for Stores', and verify error message is displayed."""
        self.home_page.load_homepage()
        self.home_page.click_find_a_store()
        self.store_locator_popup.wait_for_popup()
        self.assertTrue(self.store_locator_popup.verify_location_textbox_and_search_button(), "Location textbox and search button should be visible.")
        self.assertTrue(self.store_locator_popup.enter_location('InvalidCity123'), "Should be able to enter 'InvalidCity123'.")
        self.assertTrue(self.store_locator_popup.click_search_for_stores(), "Should be able to click search for stores.")
        self.assertTrue(self.store_locator_popup.is_no_stores_found_error_displayed(), "Error message should be displayed for invalid location 'InvalidCity123'.")

    def test_2109_store_locator_use_my_location(self):
        """Test Case 2109: Launch Foot Locker website, navigate to Store Locator, ensure browser location permission is enabled, click 'Use My Location', and verify stores near current location are displayed."""
        self.home_page.load_homepage()
        self.home_page.click_find_a_store()
        self.assertTrue(self.store_locator_popup.wait_for_popup(), "Store Locator popup should be visible.")
        self.assertTrue(self.store_locator_popup.allow_location_permission(), "Browser location permission should be enabled.")
        self.assertTrue(self.store_locator_popup.click_use_my_location(), "Should be able to click 'Use My Location' and see nearby stores.")
        self.assertTrue(self.store_locator_popup.are_store_results_displayed(), "List of stores near the user's current location should be displayed.")

    def test_2110_store_locator_boston_store_details(self):
        """Test Case 2110: Launch Foot Locker website, navigate to Store Locator, search for 'Boston, MA', click on store with address '375 Washington Street, Boston, MA 02108', and verify store details popup."""
        self.home_page.load_homepage()
        self.home_page.click_find_a_store()
        self.assertTrue(self.store_locator_popup.wait_for_popup(), "Store Locator popup should be visible.")
        self.assertTrue(self.store_locator_popup.enter_location('Boston, MA'), "Should be able to enter 'Boston, MA'.")
        self.assertTrue(self.store_locator_popup.click_search_for_stores(), "Should be able to click search for stores.")
        self.assertTrue(self.store_locator_popup.is_store_address_present_in_results('375 Washington Street, Boston, MA 02108'), "Store with address should be present in results.")
        self.assertTrue(self.store_locator_popup.click_store_by_address('375 Washington Street, Boston, MA 02108'), "Should be able to click on the store with the specified address.")
        self.assertTrue(self.store_locator_popup.verify_store_details_popup('375 Washington Street, Boston, MA 02108'), "Store details popup should display correct address.")

    def test_2111_store_locator_boston_map_view(self):
        """Test Case 2111: Launch Foot Locker website, navigate to Store Locator, search for 'Boston, MA', toggle to map view, and verify map pins are displayed."""
        self.home_page.load_homepage()
        self.home_page.click_find_a_store()
        self.assertTrue(self.store_locator_popup.wait_for_popup(), "Store Locator popup should be visible.")
        self.assertTrue(self.store_locator_popup.enter_location('Boston, MA'), "Should be able to enter 'Boston, MA'.")
        self.assertTrue(self.store_locator_popup.click_search_for_stores(), "Should be able to click search for stores.")
        self.assertTrue(self.store_locator_popup.are_store_results_displayed(), "Store results should be displayed for 'Boston, MA'.")
        self.assertTrue(self.store_locator_popup.toggle_map_view(), "Map view should display pins for each store location.")

    def test_2112_store_locator_new_york_pagination(self):
        """Test Case 2112: Launch Foot Locker website, navigate to Store Locator, search for 'New York', paginate to next page, and verify next page of store results is displayed."""
        self.home_page.load_homepage()
        self.home_page.click_find_a_store()
        self.assertTrue(self.store_locator_popup.wait_for_popup(), "Store Locator popup should be visible.")
        self.assertTrue(self.store_locator_popup.enter_location('New York'), "Should be able to enter 'New York'.")
        self.assertTrue(self.store_locator_popup.click_search_for_stores(), "Should be able to click search for stores.")
        self.assertTrue(self.store_locator_popup.are_store_results_displayed(), "Multiple pages of store results should be displayed for 'New York'.")
        self.assertTrue(self.store_locator_popup.go_to_next_page(), "Should be able to navigate to the next page of store results.")
        self.assertTrue(self.store_locator_popup.are_store_results_displayed(), "Next page of store results should be displayed.")
