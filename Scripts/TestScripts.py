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

    def test_scrum_15408_ts_001_tc_008_set_preferred_store(self):
        """
        Test Case: SCRUM-15408 TS-001 TC-008
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Enter 'Boston, MA' in the Location textbox and click 'Search for Stores'.
        4. Click 'Set My Store' for the store at '375 Washington Street, Boston, MA 02108'.
        Expected: The selected store is saved as the user’s preferred store.
        """
        self.page.launch_homepage('https://www.footlocker.com/')
        self.page.click_find_store()
        self.page.click_select_my_store()
        self.page.enter_location('Boston, MA')
        self.page.click_search_for_stores()
        self.page.click_set_my_store()
        is_preferred = self.page.verify_store_address_exact_match('375 Washington Street, Boston, MA 02108')
        self.assertTrue(is_preferred, "Preferred store was not set correctly.")

    def test_scrum_15408_ts_001_tc_009_verify_preferred_store_confirmation(self):
        """
        Test Case: SCRUM-15408 TS-001 TC-009
        Steps:
        1. Set the store at '375 Washington Street, Boston, MA 02108' as 'My Store' (as in previous test case).
        2. Observe the UI for confirmation (e.g., message, highlight, or store name in header).
        Expected: Confirmation indicator is displayed and the selected store appears consistently across the website.
        """
        self.page.launch_homepage('https://www.footlocker.com/')
        self.page.click_find_store()
        self.page.click_select_my_store()
        self.page.enter_location('Boston, MA')
        self.page.click_search_for_stores()
        self.page.click_set_my_store()
        confirmation = self.page.verify_confirmation()
        self.assertTrue(confirmation, "Confirmation indicator is not displayed.")
        is_preferred = self.page.verify_store_address_exact_match('375 Washington Street, Boston, MA 02108')
        self.assertTrue(is_preferred, "Preferred store is not shown consistently across the website.")
