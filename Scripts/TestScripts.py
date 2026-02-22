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

    def test_2077_store_locator_select_and_verify_preferred_store(self):
        ...

    def test_2078_verify_selected_store_visible_across_website(self):
        ...

    def test_2080_find_store_popup_verification(self):
        ...

    def test_2079_preferred_store_persistence_across_pages(self):
        ...

    def test_scrum_15408_ts_002_tc_002_invalid_location_shows_no_stores_found(self):
        """
        SCRUM-15408 TS-002 TC-002
        Steps:
        1. Launch homepage
        2. Click 'Find a Store'
        3. Click 'Select My Store'
        4. Enter 'InvalidLocation123'
        5. Click 'Search for Stores'
        6. Assert that a message like 'No stores found' is displayed in the popup
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.launch_homepage()
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.enter_location('InvalidLocation123')
        store_locator.click_search_for_stores()
        self.assertTrue(
            store_locator.verify_find_store_popup_message('No stores found'),
            "Expected 'No stores found' message to be displayed in the popup."
        )

    def test_scrum_15408_ts_003_tc_001_valid_location_finds_exact_store(self):
        """
        SCRUM-15408 TS-003 TC-001
        Steps:
        1. Launch homepage
        2. Click 'Find a Store'
        3. Click 'Select My Store'
        4. Enter 'Boston, MA'
        5. Click 'Search for Stores'
        6. Assert that search results include the exact address '375 Washington Street, Boston, MA 02108'
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.launch_homepage()
        store_locator.click_find_store()
        store_locator.click_select_my_store()
        store_locator.enter_location('Boston, MA')
        store_locator.click_search_for_stores()
        self.assertTrue(
            store_locator.verify_store_address_exact_match('375 Washington Street, Boston, MA 02108'),
            "Expected store address '375 Washington Street, Boston, MA 02108' to be present in search results."
        )
