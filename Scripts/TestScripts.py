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
    ...

    def test_scrum_15408_ts_001_tc_002_find_a_store_popup_elements(self):
        """
        SCRUM-15408 TS-001 TC-002
        Step 1: Launch the Foot Locker website homepage.
        Step 2: Click on the 'Find a Store' link in the header.
        Step 3: Click on the 'Select My Store' link in the popup.
        Expected: Homepage loads, popup appears, 'Find a Store' popup window opens with a 'Location' textbox and a 'Search for Stores' button.
        """
        self.driver.get('https://www.footlocker.com/')
        store_locator = StoreLocatorPage(self.driver)
        store_locator.click_find_a_store_link()
        self.assertTrue(store_locator.is_find_a_store_popup_present(), "Find a Store popup did not appear.")
        store_locator.click_select_my_store_link()
        self.assertTrue(store_locator.is_location_textbox_present(), "Location textbox is not present in popup.")
        self.assertTrue(store_locator.is_search_for_stores_button_present(), "Search for Stores button is not present in popup.")

    def test_scrum_15408_ts_002_tc_001_search_boston_ma(self):
        """
        SCRUM-15408 TS-002 TC-001
        Step 1: Launch the Foot Locker website homepage.
        Step 2: Click on the 'Find a Store' link.
        Step 3: Click on the 'Select My Store' link in the popup.
        Step 4: Enter 'Boston, MA' in the 'Location' textbox.
        Step 5: Click the 'Search for Stores' button.
        Expected: Homepage loads, popup appears, 'Find a Store' popup window opens, 'Boston, MA' is entered, and search results are displayed with relevant stores in or near Boston.
        """
        self.driver.get('https://www.footlocker.com/')
        store_locator = StoreLocatorPage(self.driver)
        store_locator.click_find_a_store_link()
        self.assertTrue(store_locator.is_find_a_store_popup_present(), "Find a Store popup did not appear.")
        store_locator.click_select_my_store_link()
        self.assertTrue(store_locator.is_location_textbox_present(), "Location textbox is not present in popup.")
        store_locator.enter_location('Boston, MA')
        store_locator.click_search_for_stores_button()
        self.assertTrue(store_locator.is_search_results_present(), "Search results are not displayed.")
        self.assertTrue(store_locator.is_store_result_for_city('Boston'), "No store results for Boston, MA found.")
