# Existing imports and code remain unchanged
import unittest
from selenium import webdriver
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup

class TestScripts(unittest.TestCase):
    # ... Existing test methods remain unchanged ...
    ...

    def test_2103_homepage_find_store_popup(self):
        ...
    def test_2104_store_locator_popup_message(self):
        ...

    def test_2105_store_locator_boston_ma(self):
        """
        TestCaseId: 2105
        Step 1: Launch the Foot Locker website and navigate to the Store Locator page.
        Step 2: Enter 'Boston, MA' in the Location textbox.
        Step 3: Click on the 'Search for Stores' button.
        """
        driver = webdriver.Chrome()
        try:
            driver.get('https://www.footlocker.com/store-locator')
            store_locator_popup = StoreLocatorPopup(driver)
            self.assertTrue(store_locator_popup.is_store_locator_page_loaded(), "Store Locator page is not displayed.")
            store_locator_popup.enter_location('Boston, MA')
            self.assertEqual(driver.find_element_by_id('store-locator-search-input').get_attribute('value'), 'Boston, MA', "Location textbox did not contain 'Boston, MA'.")
            store_locator_popup.click_search_for_stores()
            # Ideally here you would validate the list of stores is displayed; for now, check presence of results container
            # Replace with actual locator for results list if available
            results_present = len(driver.find_elements_by_xpath("//div[contains(@class, 'store-list')]")) > 0
            self.assertTrue(results_present, "List of stores in or near Boston is not displayed.")
        finally:
            driver.quit()

    def test_2106_store_locator_zip_02108(self):
        """
        TestCaseId: 2106
        Step 1: Launch the Foot Locker website and navigate to the Store Locator page.
        Step 2: Enter '02108' in the Location textbox.
        Step 3: Click on the 'Search for Stores' button.
        """
        driver = webdriver.Chrome()
        try:
            driver.get('https://www.footlocker.com/store-locator')
            store_locator_popup = StoreLocatorPopup(driver)
            self.assertTrue(store_locator_popup.is_store_locator_page_loaded(), "Store Locator page is not displayed.")
            store_locator_popup.enter_location('02108')
            self.assertEqual(driver.find_element_by_id('store-locator-search-input').get_attribute('value'), '02108', "Location textbox did not contain '02108'.")
            store_locator_popup.click_search_for_stores()
            results_present = len(driver.find_elements_by_xpath("//div[contains(@class, 'store-list')]")) > 0
            self.assertTrue(results_present, "List of stores in or near ZIP code 02108 is not displayed.")
        finally:
            driver.quit()
