import unittest
from StoreLocatorPage import StoreLocatorPage
from selenium import webdriver

class TestScripts(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.page = StoreLocatorPage(cls.driver)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    # Existing test methods ...

    def test_scrum_15408_ts_004_tc_002_set_other_and_preferred_store(self):
        ...
    def test_scrum_15408_ts_005_tc_001_set_preferred_store_with_confirmation(self):
        ...

    def test_scrum_15408_ts_001_tc_002_location_textbox_presence_and_enabled(self):
        """
        SCRUM-15408 TS-001 TC-002
        Step 1: Launch homepage (https://www.footlocker.com/)
        Step 2: Click 'Find a Store' and then 'Select My Store'
        Step 3: Check for presence and enabled state of Location textbox
        """
        self.page.launch_homepage('https://www.footlocker.com/')
        self.page.click_find_a_store()
        self.page.click_select_my_store()
        self.assertTrue(self.page.is_location_textbox_present(), "Location textbox should be present.")
        self.assertTrue(self.page.is_location_textbox_enabled(), "Location textbox should be enabled.")

    def test_scrum_15408_ts_001_tc_003_search_for_stores_boston_ma(self):
        """
        SCRUM-15408 TS-001 TC-003
        Step 1: Launch homepage (https://www.footlocker.com/)
        Step 2: Click 'Find a Store' and then 'Select My Store'
        Step 3: Enter 'Boston, MA' in Location textbox
        Step 4: Click 'Search for Stores' button
        Step 5: Verify store results in or near Boston are displayed
        """
        self.page.launch_homepage('https://www.footlocker.com/')
        self.page.click_find_a_store()
        self.page.click_select_my_store()
        self.page.enter_location('Boston, MA')
        self.page.click_search_for_stores()
        results = self.page.get_store_results()
        self.assertTrue(any('Boston' in store['city'] or 'MA' in store['state'] for store in results), "Store results should be in or near Boston, MA.")
