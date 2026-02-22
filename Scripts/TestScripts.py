
import unittest
from selenium import webdriver
from PageClasses.HomePage import HomePage
from PageClasses.StoreLocatorPopup import StoreLocatorPopup
from PageClasses.StoreSelectionPopup import StoreSelectionPopup
from PageClasses.ConfirmationPage import ConfirmationPage

class TestScripts(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.maximize_window()
        self.home_page = HomePage(self.driver)
        self.store_locator_popup = StoreLocatorPopup(self.driver)
        self.store_selection_popup = StoreSelectionPopup(self.driver)
        self.confirmation_page = ConfirmationPage(self.driver)

    def tearDown(self):
        self.driver.quit()

    # Existing test methods ...

    # Test case 2091: Validate store selection flow from homepage
    def test_2091_store_selection_from_homepage(self):
        # Navigate to home page
        self.home_page.open()
        self.assertTrue(self.home_page.is_loaded(), "Home page failed to load")

        # Open Store Locator popup
        self.home_page.click_store_locator()
        self.assertTrue(self.store_locator_popup.is_displayed(), "Store Locator popup not displayed")

        # Search for store
        self.store_locator_popup.enter_search_criteria("New York")
        self.store_locator_popup.click_search()
        self.assertTrue(self.store_locator_popup.has_results(), "No results found for 'New York'")

        # Select first store from results
        self.store_locator_popup.select_store_by_index(0)
        self.assertTrue(self.store_selection_popup.is_displayed(), "Store Selection popup not displayed")

        # Confirm store selection
        self.store_selection_popup.confirm_selection()
        self.assertTrue(self.confirmation_page.is_displayed(), "Confirmation page not displayed after store selection")

        # Validate confirmation details
        self.assertTrue(self.confirmation_page.has_store_details("New York"), "Store details do not match selection")

    # Test case 2092: Validate store selection cancellation
    def test_2092_store_selection_cancellation(self):
        # Navigate to home page
        self.home_page.open()
        self.assertTrue(self.home_page.is_loaded(), "Home page failed to load")

        # Open Store Locator popup
        self.home_page.click_store_locator()
        self.assertTrue(self.store_locator_popup.is_displayed(), "Store Locator popup not displayed")

        # Search for store
        self.store_locator_popup.enter_search_criteria("Los Angeles")
        self.store_locator_popup.click_search()
        self.assertTrue(self.store_locator_popup.has_results(), "No results found for 'Los Angeles'")

        # Select first store from results
        self.store_locator_popup.select_store_by_index(0)
        self.assertTrue(self.store_selection_popup.is_displayed(), "Store Selection popup not displayed")

        # Cancel store selection
        self.store_selection_popup.cancel_selection()
        self.assertTrue(self.home_page.is_loaded(), "Home page not displayed after cancelling store selection")

if __name__ == "__main__":
    unittest.main()