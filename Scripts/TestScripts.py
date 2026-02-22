# Existing imports and test cases remain unchanged
import unittest
from Pages.StoreLocatorPage import StoreLocatorPage
from Pages.MensSneakersPage import MensSneakersPage
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options

# ... (existing test classes and methods) ...

class StoreLocatorTests(unittest.TestCase):
    # ... (existing setUp, tearDown, and test methods) ...

    def test_TC2109_use_my_location_shows_nearby_stores(self):
        """
        TestCase 2109: Launch Foot Locker Store Locator, ensure browser location permission is enabled,
        click 'Use My Location', and verify that a list of nearby stores is displayed.
        """
        chrome_options = Options()
        # Automatically grant location permission
        chrome_options.add_experimental_option("prefs", {
            "profile.default_content_setting_values.geolocation": 1
        })
        driver = webdriver.Chrome(options=chrome_options)
        try:
            store_locator = StoreLocatorPage(driver)
            store_locator.navigate_to_store_locator()
            store_locator.click_use_my_location()
            self.assertTrue(
                store_locator.verify_nearby_stores_displayed(),
                "Nearby stores should be displayed after using location."
            )
        finally:
            driver.quit()

    def test_TC2110_search_boston_and_verify_store_details(self):
        """
        TestCase 2110: Launch Store Locator, search for 'Boston, MA', click on the store with address
        '375 Washington Street, Boston, MA 02108', and verify store details popup.
        """
        driver = webdriver.Chrome()
        try:
            store_locator = StoreLocatorPage(driver)
            store_locator.navigate_to_store_locator()
            store_locator.enter_location('Boston, MA')
            store_locator.click_search()
            found = store_locator.is_store_search_successful('375 Washington Street, Boston, MA 02108')
            self.assertTrue(
                found,
                "Store with specified address should be found in search results."
            )
            # Assuming clicking the store sets it as preferred and shows details
            mens_sneakers = MensSneakersPage(driver)
            self.assertTrue(
                mens_sneakers.is_preferred_store_set('375 Washington Street, Boston, MA 02108'),
                "Store details popup should be displayed for selected store."
            )
        finally:
            driver.quit()
