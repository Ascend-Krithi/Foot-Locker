import unittest
from selenium import webdriver
from Pages.StoreLocatorPage import StoreLocatorPage
from Pages.MensSneakersPage import MensSneakersPage

class TestScripts(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.driver.implicitly_wait(10)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    # Existing test methods remain here...
    def test_scrum_15408_ts_001_tc_002_find_a_store_popup_elements(self):
        pass  # ...existing implementation...

    def test_scrum_15408_ts_002_tc_001_search_boston_ma(self):
        pass  # ...existing implementation...

    def test_scrum_15408_ts_003_tc_002_verify_store_address_format(self):
        """
        SCRUM-15408 TS-003 TC-002
        Steps:
        1. Launch the Foot Locker website homepage.
        2. Click on the 'Find a Store' link.
        3. Click on the 'Select My Store' link.
        4. Enter 'Boston, MA' in the 'Location' textbox.
        5. Click the 'Search for Stores' button.
        6. Locate the store with address '375 Washington Street, Boston, MA 02108' in the results.
        7. Verify the address format matches '375 Washington Street, Boston, MA 02108'.
        """
        store_locator = StoreLocatorPage(self.driver, self.get_locators())
        store_locator.launch_homepage('https://www.footlocker.com/')
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.enter_location('Boston, MA')
        store_locator.click_search_for_stores()
        address = '375 Washington Street, Boston, MA 02108'
        self.assertTrue(store_locator.is_store_address_present_in_results(address), f"Store with address '{address}' not found in results.")
        self.assertTrue(store_locator.verify_store_address_exact_match(address), f"Store address does not exactly match '{address}'.")

    def test_scrum_15408_ts_004_tc_001_set_my_store(self):
        """
        SCRUM-15408 TS-004 TC-001
        Steps:
        1. Launch the Foot Locker website homepage.
        2. Click on the 'Find a Store' link.
        3. Click on the 'Select My Store' link.
        4. Enter 'Boston, MA' in the 'Location' textbox.
        5. Click the 'Search for Stores' button.
        6. Locate the store with address '375 Washington Street, Boston, MA 02108' in the results.
        7. Click the 'Set My Store' button for this store.
        """
        store_locator = StoreLocatorPage(self.driver, self.get_locators())
        store_locator.launch_homepage('https://www.footlocker.com/')
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.enter_location('Boston, MA')
        store_locator.click_search_for_stores()
        address = '375 Washington Street, Boston, MA 02108'
        self.assertTrue(store_locator.is_store_address_present_in_results(address), f"Store with address '{address}' not found in results.")
        store_locator.click_set_my_store()
        self.assertTrue(store_locator.verify_confirmation(), "The selected store was not saved as the user's preferred store.")

    def get_locators(self):
        # Dummy method to represent loading locators from Locators.json
        # In real framework, this would load the actual locators
        return {"StoreLocatorPage": {"find_store_link": {"value": "Find a Store"}, "select_my_store_button": {"value": "//button[@id='selectMyStore']"}, "location_textbox": {"value": "location"}, "search_for_stores_button": {"value": "//button[@id='searchStores']"}, "set_my_store_button": {"value": "//button[@id='setMyStore']"}, "confirmation_indicator": {"value": ".confirmation"}}}
