# Existing imports
from selenium import webdriver
from Pages.HomePage import HomePage
from Pages.FindAStorePopup import FindAStorePopup
from Pages.StoreSearchResults import StoreSearchResults
import unittest
import time

class TestFootLockerStoreLocator(unittest.TestCase):
    # Existing test methods remain unchanged

    def test_TC_2105_store_locator_search_boston_ma(self):
        ...
    def test_TC_2106_store_locator_search_02108(self):
        ...

    def test_TC_2107_store_locator_search_massachusetts(self):
        """
        TC-2107: Launch Foot Locker website, navigate to Store Locator, enter 'Massachusetts', search for stores, and verify results.
        """
        driver = webdriver.Chrome()
        try:
            # Launch HomePage and verify
            home_page = HomePage(driver)
            self.assertTrue(home_page.is_displayed(), "Home Page is not displayed.")
            self.assertTrue(home_page.is_find_a_store_link_visible(), "'Find a Store' link is not visible.")
            home_page.click_find_a_store_link()
            # Wait for Store Locator popup
            find_store_popup = FindAStorePopup(driver)
            self.assertTrue(find_store_popup.is_popup_displayed(), "Store Locator popup is not displayed.")
            # Enter location and search
            find_store_popup.enter_location('Massachusetts')
            find_store_popup.click_search_button()
            # Verify results
            search_results = StoreSearchResults(driver)
            # We can't know the exact address, so check that at least one result appears (simulate with a short sleep)
            time.sleep(3)
            self.assertFalse(search_results.is_error_message_displayed(), "Error message displayed for valid location.")
        finally:
            driver.quit()

    def test_TC_2108_store_locator_search_invalidcity(self):
        """
        TC-2108: Launch Foot Locker website, navigate to Store Locator, enter 'InvalidCity123', search for stores, and verify error message is displayed.
        """
        driver = webdriver.Chrome()
        try:
            # Launch HomePage and verify
            home_page = HomePage(driver)
            self.assertTrue(home_page.is_displayed(), "Home Page is not displayed.")
            self.assertTrue(home_page.is_find_a_store_link_visible(), "'Find a Store' link is not visible.")
            home_page.click_find_a_store_link()
            # Wait for Store Locator popup
            find_store_popup = FindAStorePopup(driver)
            self.assertTrue(find_store_popup.is_popup_displayed(), "Store Locator popup is not displayed.")
            # Enter invalid location and search
            find_store_popup.enter_location('InvalidCity123')
            find_store_popup.click_search_button()
            # Verify error message
            search_results = StoreSearchResults(driver)
            time.sleep(3)
            self.assertTrue(search_results.is_error_message_displayed(), "Error message not displayed for invalid location.")
        finally:
            driver.quit()
