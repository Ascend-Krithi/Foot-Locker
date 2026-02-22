import unittest
from StoreLocatorPage import StoreLocatorPage

class StoreLocatorTests(unittest.TestCase):
    # Existing test methods...

    def test_scrum_15408_ts_sl_012_tc_001_api_failure(self):
        """
        SCRUM-15408 TS-SL-012 TC-001
        Steps:
        1. Simulate API/network failure
        2. Attempt store search for 'Boston, MA'
        3. Assert error message and no results
        """
        page = StoreLocatorPage(self.driver)
        # Step 1: Simulate API/network failure
        page.simulate_api_failure()
        # Step 2: Attempt store search for 'Boston, MA' with API down
        page.search_store_with_api_down('Boston, MA')
        # Step 3: Assert error message and no store results
        self.assertTrue(page.verify_api_failure_error_displayed(), "User-friendly error message should be displayed and no store results should be shown when API fails.")

    def test_scrum_15408_ts_sl_008_tc_001(self):
        """
        SCRUM-15408 TS-SL-008 TC-001
        Steps:
        1. Launch the Foot Locker website and navigate to the Store Locator page.
        2. Perform a search for 'Boston, MA'.
        3. Toggle to the map view.
        """
        page = StoreLocatorPage(self.driver)
        # Step 1: Launch and verify Store Locator page
        self.assertTrue(page.access_store_locator_page(device_type='desktop'))
        # Step 2: Search for 'Boston, MA' and verify results
        self.assertTrue(page.search_for_location('Boston, MA'))
        self.assertTrue(page.verify_store_results('Boston'))
        # Step 3: Toggle to map view and verify pins
        self.assertTrue(page.toggle_map_view())
        self.assertTrue(page.verify_map_pins())

    def test_scrum_15408_ts_sl_009_tc_001(self):
        """
        SCRUM-15408 TS-SL-009 TC-001
        Steps:
        1. Launch the Foot Locker website and navigate to the Store Locator page.
        2. Perform a search for 'New York'.
        3. Use the pagination controls to navigate to the next page.
        """
        page = StoreLocatorPage(self.driver)
        # Step 1: Launch and verify Store Locator page
        self.assertTrue(page.access_store_locator_page(device_type='desktop'))
        # Step 2: Search for 'New York' and verify pagination
        self.assertTrue(page.search_for_location('New York'))
        self.assertTrue(page.verify_store_results('New York'))
        self.assertTrue(page.verify_pagination())
        # Step 3: Paginate to next page and verify results
        self.assertTrue(page.paginate_store_results())
        self.assertTrue(page.verify_store_results('New York'))

if __name__ == "__main__":
    unittest.main()
