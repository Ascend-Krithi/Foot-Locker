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
        page = StoreLocatorPage()
        # Step 1: Simulate API/network failure
        page.simulate_api_failure()
        # Step 2: Attempt store search for 'Boston, MA' with API down
        page.search_store_with_api_down('Boston, MA')
        # Step 3: Assert error message and no store results
        self.assertTrue(page.verify_api_failure_error_displayed(), "User-friendly error message should be displayed and no store results should be shown when API fails.")

if __name__ == "__main__":
    unittest.main()
