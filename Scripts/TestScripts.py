# Existing imports and code remain unchanged
import unittest
from selenium import webdriver
from PageClasses.HomePage import HomePage
from PageClasses.StoreLocatorPopup import StoreLocatorPopup
from PageClasses.StoreSelectionPopup import StoreSelectionPopup
from PageClasses.ConfirmationPage import ConfirmationPage

class TestScripts(unittest.TestCase):
    # ... Existing test methods remain unchanged ...

    def test_2097_homepage_store_locator_search(self):
        """
        TestCase 2097:
        1. Load homepage and validate.
        2. Click 'Find a Store' and 'Select My Store', validate Store Locator Popup.
        3. Enter 'Boston, MA' and search, validate store results displayed.
        """
        driver = webdriver.Chrome()
        try:
            home = HomePage(driver)
            home.load()
            self.assertTrue(home.is_loaded(), "Homepage did not load correctly.")

            home.click_find_a_store()
            home.click_select_my_store()

            locator_popup = StoreLocatorPopup(driver)
            self.assertTrue(locator_popup.is_displayed(), "Store Locator Popup not displayed.")

            locator_popup.enter_location("Boston, MA")
            locator_popup.click_search()

            selection_popup = StoreSelectionPopup(driver)
            self.assertTrue(selection_popup.results_displayed(), "Store results were not displayed after search.")
        finally:
            driver.quit()

    def test_2098_homepage_store_locator_search_with_address_validation(self):
        """
        TestCase 2098:
        1. Load homepage and validate.
        2. Click 'Find a Store' and 'Select My Store', validate Store Locator Popup.
        3. Enter 'Boston, MA' and search, validate store results displayed.
        4. Validate store with address '375 Washington Street, Boston, MA 02108' is present in results.
        """
        driver = webdriver.Chrome()
        try:
            home = HomePage(driver)
            home.load()
            self.assertTrue(home.is_loaded(), "Homepage did not load correctly.")

            home.click_find_a_store()
            home.click_select_my_store()

            locator_popup = StoreLocatorPopup(driver)
            self.assertTrue(locator_popup.is_displayed(), "Store Locator Popup not displayed.")

            locator_popup.enter_location("Boston, MA")
            locator_popup.click_search()

            selection_popup = StoreSelectionPopup(driver)
            self.assertTrue(selection_popup.results_displayed(), "Store results were not displayed after search.")
            self.assertTrue(
                selection_popup.store_with_address_displayed("375 Washington Street, Boston, MA 02108"),
                "Expected store address was not found in results."
            )
        finally:
            driver.quit()
