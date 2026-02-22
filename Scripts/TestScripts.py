
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
