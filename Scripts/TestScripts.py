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

    @pytest.mark.tc2081
    def test_TC2081_store_locator_popup_ui_elements(self, driver):
        """
        TC ID: 2081
        Test that the Store Locator popup window opens with 'Location' textbox and 'Search for Stores' button.
        Steps:
        1. Launch the Foot Locker website homepage.
        2. Click on the 'Find a Store' link in the header.
        3. Click on the 'Select My Store' link in the popup.
        Expected: Homepage loads, popup appears, and the store locator popup window opens with a 'Location' textbox and a 'Search for Stores' button.
        """
        home_page = HomePage(driver)
        store_locator_popup = StoreLocatorPopup(driver)

        # Step 1: Launch the homepage
        home_page.load_homepage()

        # Step 2: Click on the 'Find a Store' link in the header.
        home_page.click_find_a_store()

        # Step 3: Wait for popup, then click on 'Select My Store' in the popup.
        store_locator_popup.wait_for_popup()
        store_locator_popup.click_select_my_store()

        # Expected: Store locator popup window opens with 'Location' textbox and 'Search for Stores' button.
        assert store_locator_popup.verify_location_textbox_and_search_button(), (
            "Store Locator popup should display 'Location' textbox and 'Search for Stores' button."
        )

    @pytest.mark.tc2082
    def test_TC2082_store_locator_search_boston(self, driver):
        """
        TC ID: 2082
        Test searching for stores in Boston, MA using the store locator popup.
        Steps:
        1. Launch the homepage
        2. Click 'Find a Store'
        3. Click 'Select My Store'
        4. Enter 'Boston, MA' in the location textbox
        5. Click 'Search for Stores'
        Expected: Homepage loads, popup appears, store locator popup opens, location entered, and search results for Boston displayed.
        """
        home_page = HomePage(driver)
        store_locator_popup = StoreLocatorPopup(driver)

        # Step 1: Launch the homepage
        home_page.load_homepage()

        # Step 2: Click 'Find a Store'
        home_page.click_find_a_store()

        # Step 3: Wait for popup, then click 'Select My Store'
        store_locator_popup.wait_for_popup()
        store_locator_popup.click_select_my_store()

        # Step 4: Enter 'Boston, MA' in the location textbox
        store_locator_popup.enter_location("Boston, MA")

        # Step 5: Click 'Search for Stores'
        store_locator_popup.click_search_for_stores()

        # Expected: search results for Boston are displayed
        assert store_locator_popup.verify_search_results_for_location("Boston, MA"), (
            "Search results for 'Boston, MA' should be displayed in the Store Locator popup."
        )
