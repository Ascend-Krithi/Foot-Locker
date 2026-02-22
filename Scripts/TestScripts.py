import unittest
from selenium import webdriver
from StoreLocatorPage import StoreLocatorPage

class StoreLocatorTests(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        # Example locators dict, update as needed
        self.locators = {
            'find_store_button': '//*[@id="find-store"]',
            'find_store_popup': '//*[@id="find-store-popup"]',
            'find_store_popup_message': '//*[@id="find-store-popup-message"]',
            'select_my_store_link': '//*[@id="select-my-store"]',
            'store_locator_popup': '//*[@id="store-locator-popup"]',
            'location_textbox': '//*[@id="location-textbox"]',
            'search_for_stores_button': '//*[@id="search-stores"]'
        }
        self.page = StoreLocatorPage(self.driver, self.locators)

    def tearDown(self):
        self.driver.quit()

    # Existing test methods...

    def test_2073_verify_find_store_popup_and_elements(self):
        """testCaseId 2073: Launch homepage, click 'Find a Store', verify popup, message, and 'Select My Store' link"""
        self.page.launch_homepage()
        self.page.click_find_store()
        self.assertTrue(self.page.is_find_store_popup_displayed(), "Find Store popup should be displayed")
        message = self.page.get_find_store_popup_message()
        self.assertIsNotNone(message, "Find Store popup message should be present")
        self.assertTrue(len(message) > 0, "Find Store popup message should not be empty")
        self.assertTrue(self.page.is_select_my_store_link_visible(), "'Select My Store' link should be visible")

    def test_2074_verify_store_locator_popup_and_elements(self):
        """testCaseId 2074: Launch homepage, click 'Find a Store', click 'Select My Store', verify popup, textbox, button"""
        self.page.launch_homepage()
        self.page.click_find_store()
        self.page.click_select_my_store()
        self.assertTrue(self.page.is_store_locator_popup_displayed(), "Store Locator popup should be displayed")
        self.assertTrue(self.page.is_location_textbox_present(), "Location textbox should be present in Store Locator popup")
        self.assertTrue(self.page.is_search_for_stores_button_present(), "Search for Stores button should be present in Store Locator popup")

    def test_2075_search_stores_in_boston(self):
        """testCaseId 2075: Launch homepage, open store locator popup, enter 'Boston, MA', search for stores."""
        self.page.launch_homepage('https://www.footlocker.com/')
        self.page.open_store_locator_popup()
        self.page.enter_location('Boston, MA')
        self.page.search_for_stores()
        # Optionally, add assertion for search results if confirmation indicator is available

    def test_2076_verify_boston_store_address(self):
        """testCaseId 2076: Launch homepage, open store locator popup, enter 'Boston, MA', search for stores, verify store address."""
        self.page.launch_homepage('https://www.footlocker.com/')
        self.page.open_store_locator_popup()
        self.page.enter_location('Boston, MA')
        self.page.search_for_stores()
        address = '375 Washington Street, Boston, MA 02108'
        self.assertTrue(self.page.is_store_address_visible(address), f"Store address '{address}' should be visible in the results.")
        self.assertTrue(self.page.verify_store_address_match(address), f"Store address should match exactly: '{address}'")
