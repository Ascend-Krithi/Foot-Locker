import unittest
from StoreLocatorPage import StoreLocatorPage
from MensSneakersPage import MensSneakersPage
from WomensShoesPage import WomensShoesPage
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

    def test_scrum_15408_ts_004_tc_002_set_other_and_preferred_store(self): ...
    def test_scrum_15408_ts_005_tc_001_set_preferred_store_with_confirmation(self): ...
    def test_scrum_15408_ts_005_tc_002_store_locator_and_mens_page(self): ...
    def test_scrum_15408_ts_006_tc_001_store_locator_and_womens_page(self): ...
    def test_scrum_15408_ts_001_tc_002_store_locator_location_textbox(self): ...
    def test_scrum_15408_ts_001_tc_003_store_locator_search_boston(self): ...

    def test_scrum_15408_ts_001_tc_008_set_preferred_store(self): ...
    def test_scrum_15408_ts_001_tc_009_verify_preferred_store_confirmation(self): ...

    def test_scrum_15408_ts_001_tc_010_preferred_store_persistence_navigation(self): ...
    def test_scrum_15408_ts_001_tc_011_preferred_store_persistence_browser_restart(self): ...

    def test_scrum_15408_ts_sl_002_tc_001_search_store_by_boston_ma(self):
        """
        SCRUM-15408 TS-SL-002 TC-001
        1. Launch the Foot Locker website and navigate to the Store Locator page.
        2. Enter 'Boston, MA' in the Location textbox.
        3. Click on the 'Search for Stores' button.
        4. Verify that the list of stores in or near Boston is displayed.
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.open_store_locator(url="https://www.footlocker.com/store-locator")
        store_locator.enter_location("Boston, MA")
        store_locator.click_search_for_stores()
        self.assertTrue(store_locator.verify_store_list_displayed(), "Store list is not displayed for location 'Boston, MA'.")

    def test_scrum_15408_ts_sl_003_tc_001_search_store_by_zip_02108(self):
        """
        SCRUM-15408 TS-SL-003 TC-001
        1. Launch the Foot Locker website and navigate to the Store Locator page.
        2. Enter '02108' in the Location textbox.
        3. Click on the 'Search for Stores' button.
        4. Verify that the list of stores in or near ZIP code 02108 is displayed.
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.open_store_locator(url="https://www.footlocker.com/store-locator")
        store_locator.enter_location("02108")
        store_locator.click_search_for_stores()
        self.assertTrue(store_locator.verify_store_list_displayed(), "Store list is not displayed for ZIP code '02108'.")
