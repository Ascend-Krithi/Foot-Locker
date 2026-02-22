
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
        self.home_page.go_to_homepage()
        self.home_page.click_store_locator()
        self.store_locator_popup.wait_for_popup()
        self.store_locator_popup.enter_location('Massachusetts')
        self.store_locator_popup.click_search()
        self.assertTrue(
            self.store_locator_popup.is_store_list_displayed(),
            "Store list should be displayed for valid location 'Massachusetts'."
        )

    def test_2108_store_locator_invalid_city(self):
        """Test Case 2108: Launch Foot Locker website, navigate to Store Locator, enter 'InvalidCity123', click 'Search for Stores', and verify error message is displayed."""
        self.home_page.go_to_homepage()
        self.home_page.click_store_locator()
        self.store_locator_popup.wait_for_popup()
        self.store_locator_popup.enter_location('InvalidCity123')
        self.store_locator_popup.click_search()
        self.assertTrue(
            self.store_locator_popup.is_error_message_displayed(),
            "Error message should be displayed for invalid location 'InvalidCity123'."
        )
