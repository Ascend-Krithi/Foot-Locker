# Existing imports and code remain unchanged
import unittest
from selenium import webdriver
from PageClasses.HomePage import HomePage
from PageClasses.StoreLocatorPopup import StoreLocatorPopup
from PageClasses.StoreSelectionPopup import StoreSelectionPopup
from PageClasses.ConfirmationPage import ConfirmationPage
from PageClasses.ProductListingPage import ProductListingPage

class TestScripts(unittest.TestCase):
    # ... Existing test methods remain unchanged ...

    def test_2097_homepage_store_locator_search(self):
        ...
    def test_2098_homepage_store_locator_search_with_address_validation(self):
        ...

    def test_2099_set_preferred_store(self):
        ...
    def test_2100_store_confirmation_and_persistence(self):
        ...

    def test_2101_store_persistence_after_navigation(self):
        """
        Test Case 2101:
        1. Set the store at '375 Washington Street, Boston, MA 02108' as 'My Store'.
        2. Navigate to Men's Shoes (product listing).
        3. Return to homepage.
        4. Click 'Find a Store' and 'Select My Store' again. Validate that 'My Store' is still set.
        """
        driver = webdriver.Chrome()
        try:
            driver.get("https://your-site-homepage.com")
            home_page = HomePage(driver)
            home_page.click_find_store()

            store_locator_popup = StoreLocatorPopup(driver)
            store_locator_popup.search_store("375 Washington Street, Boston, MA 02108")
            store_locator_popup.select_store("375 Washington Street, Boston, MA 02108")

            store_selection_popup = StoreSelectionPopup(driver)
            store_selection_popup.set_my_store()

            confirmation_page = ConfirmationPage(driver)
            self.assertTrue(confirmation_page.is_my_store_confirmation_displayed("375 Washington Street, Boston, MA 02108"),
                            "My Store confirmation not displayed after setting store.")

            # Navigate to Men's Shoes (product listing)
            home_page.navigate_to_mens_shoes()
            product_listing_page = ProductListingPage(driver)
            self.assertTrue(product_listing_page.is_my_store_indicator_displayed("375 Washington Street, Boston, MA 02108"),
                            "My Store indicator not displayed on product listing page.")

            # Return to homepage
            product_listing_page.navigate_to_homepage()
            self.assertTrue(home_page.is_my_store_indicator_displayed("375 Washington Street, Boston, MA 02108"),
                            "My Store indicator not displayed on homepage after navigation.")

            # Click 'Find a Store' and 'Select My Store' again
            home_page.click_find_store()
            store_locator_popup.select_my_store()
            self.assertTrue(store_selection_popup.is_my_store_selected("375 Washington Street, Boston, MA 02108"),
                            "My Store is not persisted after navigation.")
        finally:
            driver.quit()

    def test_2102_store_persistence_after_browser_restart(self):
        """
        Test Case 2102:
        1. Set the store at '375 Washington Street, Boston, MA 02108' as 'My Store'.
        2. Close the browser completely.
        3. Reopen browser and navigate to homepage.
        4. Click 'Find a Store' and 'Select My Store' again. Validate that 'My Store' is still set.
        """
        # First session: set My Store
        driver = webdriver.Chrome()
        try:
            driver.get("https://your-site-homepage.com")
            home_page = HomePage(driver)
            home_page.click_find_store()

            store_locator_popup = StoreLocatorPopup(driver)
            store_locator_popup.search_store("375 Washington Street, Boston, MA 02108")
            store_locator_popup.select_store("375 Washington Street, Boston, MA 02108")

            store_selection_popup = StoreSelectionPopup(driver)
            store_selection_popup.set_my_store()

            confirmation_page = ConfirmationPage(driver)
            self.assertTrue(confirmation_page.is_my_store_confirmation_displayed("375 Washington Street, Boston, MA 02108"),
                            "My Store confirmation not displayed after setting store.")
        finally:
            driver.quit()

        # Second session: verify persistence
        driver = webdriver.Chrome()
        try:
            driver.get("https://your-site-homepage.com")
            home_page = HomePage(driver)
            self.assertTrue(home_page.is_my_store_indicator_displayed("375 Washington Street, Boston, MA 02108"),
                            "My Store indicator not displayed on homepage after browser restart.")

            home_page.click_find_store()
            store_locator_popup = StoreLocatorPopup(driver)
            store_locator_popup.select_my_store()
            store_selection_popup = StoreSelectionPopup(driver)
            self.assertTrue(store_selection_popup.is_my_store_selected("375 Washington Street, Boston, MA 02108"),
                            "My Store is not persisted after browser restart.")
        finally:
            driver.quit()
