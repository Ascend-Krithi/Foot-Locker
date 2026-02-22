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

    def test_scrum_15408_ts_004_tc_002_set_other_and_preferred_store(self):
        """
        Test Case: SCRUM-15408 TS-004 TC-002
        Steps:
        1. Launch homepage
        2. Click 'Find a Store'
        3. Click 'Select My Store'
        4. Enter 'Boston, MA'
        5. Click 'Search for Stores'
        6. Click 'Set My Store' for a store other than '375 Washington Street, Boston, MA 02108'
        7. Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'
        """
        self.page.launch_homepage()
        self.page.click_find_a_store()
        self.page.click_select_my_store()
        self.page.enter_store_search('Boston, MA')
        self.page.click_search_for_stores()

        stores = self.page.get_search_results()
        # Find a store other than the target
        other_store = None
        for store in stores:
            if store['address'] != '375 Washington Street, Boston, MA 02108':
                other_store = store
                break
        self.assertIsNotNone(other_store, "No other store found in search results.")
        self.page.set_my_store_by_address(other_store['address'])

        # Now set preferred store
        self.page.set_my_store_by_address('375 Washington Street, Boston, MA 02108')
        # Optionally verify preferred store is set
        preferred = self.page.get_preferred_store()
        self.assertEqual(preferred['address'], '375 Washington Street, Boston, MA 02108')

    def test_scrum_15408_ts_005_tc_001_set_preferred_store_with_confirmation(self):
        """
        Test Case: SCRUM-15408 TS-005 TC-001
        Steps:
        1. Launch homepage
        2. Click 'Find a Store'
        3. Click 'Select My Store'
        4. Enter 'Boston, MA'
        5. Click 'Search for Stores'
        6. Click 'Set My Store' for '375 Washington Street, Boston, MA 02108' and verify confirmation indicator
        """
        self.page.launch_homepage()
        self.page.click_find_a_store()
        self.page.click_select_my_store()
        self.page.enter_store_search('Boston, MA')
        self.page.click_search_for_stores()
        self.page.set_my_store_by_address('375 Washington Street, Boston, MA 02108')

        # Verify confirmation indicator
        confirmation = self.page.get_set_store_confirmation('375 Washington Street, Boston, MA 02108')
        self.assertTrue(confirmation, "Confirmation indicator not found for preferred store.")

    def test_scrum_15408_ts_005_tc_002_store_locator_and_mens_page(self):
        """
        Test Case: SCRUM-15408 TS-005 TC-002
        Steps:
        1. Launch homepage
        2. Click 'Find a Store'
        3. Click 'Select My Store'
        4. Enter 'Boston, MA'
        5. Click 'Search for Stores'
        6. Set '375 Washington Street, Boston, MA 02108' as preferred store
        7. Navigate to Men's Shoes page and verify the preferred store indicator is correct
        """
        self.page.launch_homepage()
        self.page.click_find_a_store()
        self.page.click_select_my_store()
        self.page.enter_store_search('Boston, MA')
        self.page.click_search_for_stores()
        self.page.set_my_store_by_address('375 Washington Street, Boston, MA 02108')

        # Optionally verify preferred store is set in locator page
        preferred = self.page.get_preferred_store()
        self.assertEqual(preferred['address'], '375 Washington Street, Boston, MA 02108')

        # Navigate to Men's Shoes page
        mens_page = MensSneakersPage(self.driver)
        mens_page.go_to_page()
        # Verify preferred store indicator
        store_indicator = mens_page.get_preferred_store_indicator()
        self.assertIsNotNone(store_indicator, "Preferred store indicator not found on Men's Shoes page.")
        self.assertIn('375 Washington Street, Boston, MA 02108', store_indicator)

    def test_scrum_15408_ts_006_tc_001_store_locator_and_womens_page(self):
        """
        Test Case: SCRUM-15408 TS-006 TC-001
        Steps:
        1. Launch homepage
        2. Click 'Find a Store'
        3. Click 'Select My Store'
        4. Enter 'Boston, MA'
        5. Click 'Search for Stores'
        6. Set '375 Washington Street, Boston, MA 02108' as preferred store
        7. Navigate to Women's Shoes page and verify the preferred store indicator is correct
        """
        self.page.launch_homepage()
        self.page.click_find_a_store()
        self.page.click_select_my_store()
        self.page.enter_store_search('Boston, MA')
        self.page.click_search_for_stores()
        self.page.set_my_store_by_address('375 Washington Street, Boston, MA 02108')

        # Optionally verify preferred store is set in locator page
        preferred = self.page.get_preferred_store()
        self.assertEqual(preferred['address'], '375 Washington Street, Boston, MA 02108')

        # Navigate to Women's Shoes page
        womens_page = WomensShoesPage(self.driver)
        womens_page.go_to_page()
        # Verify preferred store indicator
        store_indicator = womens_page.get_preferred_store_indicator()
        self.assertIsNotNone(store_indicator, "Preferred store indicator not found on Women's Shoes page.")
        self.assertIn('375 Washington Street, Boston, MA 02108', store_indicator)

    # Newly added test for SCRUM-15408 TS-001 TC-004
    def test_scrum_15408_ts_001_tc_004_search_boston_by_zip(self):
        """
        Test Case: SCRUM-15408 TS-001 TC-004
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Enter '02108' in the Location textbox.
        4. Click the 'Search for Stores' button.
        5. Verify store results in or near Boston are displayed.
        """
        self.page.launch_homepage('https://www.footlocker.com/')
        self.page.click_find_store()
        self.page.click_select_my_store()
        self.page.enter_location('02108')
        self.page.click_search_for_stores()
        # You may want to verify a specific store address or check for any Boston-related address
        found = self.page.is_store_address_present_in_results('Boston')
        self.assertTrue(found, "No store results found in or near Boston for ZIP 02108.")

    # Newly added test for SCRUM-15408 TS-001 TC-005
    def test_scrum_15408_ts_001_tc_005_search_nome_alaska(self):
        """
        Test Case: SCRUM-15408 TS-001 TC-005
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Enter 'Nome, Alaska' in the Location textbox.
        4. Click the 'Search for Stores' button.
        5. Verify 'No stores found near this location' message is displayed.
        """
        self.page.launch_homepage('https://www.footlocker.com/')
        self.page.click_find_store()
        self.page.click_select_my_store()
        self.page.enter_location('Nome, Alaska')
        self.page.click_search_for_stores()
        # Check that the popup message for no stores found is present
        found = self.page.verify_find_store_popup_message('No stores found near this location')
        self.assertTrue(found, "'No stores found near this location' message was not displayed for Nome, Alaska.")
