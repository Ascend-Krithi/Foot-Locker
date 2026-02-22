# Existing imports and code remain unchanged
import unittest
from selenium.webdriver.common.by import By
from StoreLocatorPopup import StoreLocatorPopup

class TestStoreLocator(unittest.TestCase):
    # ... existing test methods ...

    def test_store_locator_api_unavailable_2115(self):
        """
        testCaseId: 2115 (SCRUM-15408 TS-SL-012 TC-001)
        Steps:
        1. Simulate the store locator API being unavailable (assume mock/stub or comment on network failure simulation if not feasible)
        2. Attempt to perform a store search for 'Boston, MA'
        3. Assert that a user-friendly error message is displayed
        4. Assert that no results are displayed
        """
        driver = self.driver  # Assume self.driver is set up in setUp method
        store_locator_popup = StoreLocatorPopup(driver)

        # Step 1: Simulate API failure
        # NOTE: In automation, this would use a mock/stub or network interception.
        # If not feasible, comment here:
        # Example: Use a proxy or mock server to return a 500 error for the store locator API.
        # For this test, ensure the backend is returning an error or the UI is configured to simulate the failure.
        # If not possible, this step should be coordinated with the test environment.

        # Step 2: Attempt to perform a store search for 'Boston, MA'
        store_locator_popup.open()
        store_locator_popup.enter_search_text('Boston, MA')
        store_locator_popup.click_search_button()

        # Step 3: Assert that a user-friendly error message is displayed
        self.assertTrue(
            store_locator_popup.is_error_message_displayed(),
            "Error message should be displayed when API is unavailable."
        )
        error_text = store_locator_popup.get_error_message_text()
        self.assertIsInstance(error_text, str)
        self.assertGreater(len(error_text), 0, "Error message text should not be empty.")

        # Step 4: Assert that no results are displayed
        self.assertFalse(
            store_locator_popup.are_results_displayed(),
            "No store results should be displayed when API is unavailable."
        )
