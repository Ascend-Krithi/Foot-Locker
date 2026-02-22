# Existing imports
import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup

class TestFootLockerStoreLocator:
    # ... (existing test methods remain unchanged)

    @pytest.mark.tc2091
    def test_TC2091_store_locator_popup_display(self, driver):
        """
        TC ID: 2091 (SCRUM-15408 TS-006 TC-002)
        Test that the Store Locator popup appears when the Store Locator link is clicked from the home page.
        Steps:
        1. Navigate to the home page
        2. Click the Store Locator link
        3. Verify that the Store Locator popup is displayed
        """
        home_page = HomePage(driver)
        store_locator_popup = StoreLocatorPopup(driver)
        
        # Step 1: Navigate to home page (assume driver is already on home page)
        # Step 2: Click the Store Locator link
        home_page.click_store_locator_link()
        
        # Step 3: Verify that the Store Locator popup is displayed
        assert store_locator_popup.is_popup_displayed(), "Store Locator popup should be displayed after clicking the link."

    @pytest.mark.tc2092
    def test_TC2092_store_locator_search_and_results(self, driver):
        """
        TC ID: 2092 (SCRUM-15408 TS-001 TC-001)
        Test that searching for a store by ZIP code displays results in the Store Locator popup.
        Steps:
        1. Navigate to the home page
        2. Click the Store Locator link
        3. Enter a valid ZIP code and submit
        4. Verify that search results are displayed
        """
        home_page = HomePage(driver)
        store_locator_popup = StoreLocatorPopup(driver)
        
        # Step 1: Navigate to home page (assume driver is already on home page)
        # Step 2: Click the Store Locator link
        home_page.click_store_locator_link()
        
        # Wait for popup to be visible
        assert store_locator_popup.is_popup_displayed(), "Store Locator popup should be displayed after clicking the link."
        
        # Step 3: Enter a valid ZIP code and submit
        store_locator_popup.enter_zip_code("10001")
        store_locator_popup.click_search_button()
        
        # Step 4: Verify that search results are displayed
        assert store_locator_popup.has_search_results(), "Search results should be displayed for valid ZIP code."
