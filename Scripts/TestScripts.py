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
        """
        TestCase 2099:
        1. Launch the Foot Locker website and navigate to the homepage (URL: https://www.footlocker.com/). Expected: Homepage is displayed.
        2. Click 'Find a Store' and then 'Select My Store'. Expected: Store Locator Popup is displayed.
        3. Enter 'Boston, MA' in the Location textbox and click 'Search for Stores'. Expected: Store results are displayed.
        4. Click 'Set My Store' for the store at '375 Washington Street, Boston, MA 02108'. Expected: The selected store is saved as the user’s preferred store.
        """
        driver = webdriver.Chrome()
        try:
            homepage = HomePage(driver)
            homepage.launch_homepage("https://www.footlocker.com/")
            self.assertTrue(homepage.is_homepage_displayed(), "Homepage is not displayed.")

            homepage.click_find_a_store()
            store_locator = StoreLocatorPopup(driver)
            store_locator.click_select_my_store()
            # Assuming a method to verify popup is displayed, or rely on next steps
            store_locator.enter_location("Boston, MA")
            store_locator.click_search_for_stores()

            store_selection = StoreSelectionPopup(driver)
            store_selection.click_set_my_store("375 Washington Street, Boston, MA 02108")
            # Optionally, add assertion if UI feedback is immediate
        finally:
            driver.quit()

    def test_2100_store_confirmation_and_persistence(self):
        """
        TestCase 2100:
        1. Set the store at '375 Washington Street, Boston, MA 02108' as 'My Store' (as in previous test case). Expected: Store is set as preferred.
        2. Observe the UI for confirmation (e.g., message, highlight, or store name in header). Expected: Confirmation indicator is displayed and the selected store appears consistently across the website.
        """
        driver = webdriver.Chrome()
        try:
            homepage = HomePage(driver)
            homepage.launch_homepage("https://www.footlocker.com/")
            self.assertTrue(homepage.is_homepage_displayed(), "Homepage is not displayed.")

            homepage.click_find_a_store()
            store_locator = StoreLocatorPopup(driver)
            store_locator.click_select_my_store()
            store_locator.enter_location("Boston, MA")
            store_locator.click_search_for_stores()

            store_selection = StoreSelectionPopup(driver)
            store_selection.click_set_my_store("375 Washington Street, Boston, MA 02108")

            confirmation_page = ConfirmationPage(driver)
            self.assertTrue(confirmation_page.is_confirmation_displayed(), "Confirmation indicator is not displayed.")

            product_listing = ProductListingPage(driver)
            self.assertTrue(product_listing.is_my_store_indicator_displayed("375 Washington Street, Boston, MA 02108"), "Selected store is not indicated in product listing.")
        finally:
            driver.quit()
