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
        """
        TC-2105: Launch Foot Locker website, navigate to Store Locator, enter 'Boston, MA', search for stores, and verify results.
        """
        driver = webdriver.Chrome()
        try:
            # Launch HomePage
            home_page = HomePage(driver)
            home_page.load()
            # Open Find a Store popup
            home_page.click_find_a_store()
            # Wait for popup
            find_store_popup = FindAStorePopup(driver)
            find_store_popup.wait_until_visible()
            # Enter location and search
            find_store_popup.enter_location('Boston, MA')
            find_store_popup.click_search()
            # Wait and verify results
            search_results = StoreSearchResults(driver)
            search_results.wait_until_results_loaded()
            results = search_results.get_store_results()
            self.assertGreater(len(results), 0, "No stores found for 'Boston, MA'")
        finally:
            driver.quit()

    def test_TC_2106_store_locator_search_02108(self):
        """
        TC-2106: Launch Foot Locker website, navigate to Store Locator, enter '02108', search for stores, and verify results.
        """
        driver = webdriver.Chrome()
        try:
            # Launch HomePage
            home_page = HomePage(driver)
            home_page.load()
            # Open Find a Store popup
            home_page.click_find_a_store()
            # Wait for popup
            find_store_popup = FindAStorePopup(driver)
            find_store_popup.wait_until_visible()
            # Enter location and search
            find_store_popup.enter_location('02108')
            find_store_popup.click_search()
            # Wait and verify results
            search_results = StoreSearchResults(driver)
            search_results.wait_until_results_loaded()
            results = search_results.get_store_results()
            self.assertGreater(len(results), 0, "No stores found for '02108'")
        finally:
            driver.quit()
