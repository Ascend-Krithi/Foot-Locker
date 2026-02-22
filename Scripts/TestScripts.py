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

    @pytest.mark.tc2093
    def test_TC2093_find_store_and_location_textbox(self, driver):
        """
        TC ID: 2093 (SCRUM-15408 TS-001 TC-002)
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Check for the presence and enabled state of the Location textbox.
        """
        home_page = HomePage(driver)
        store_locator_popup = StoreLocatorPopup(driver)
        
        # Step 1: Launch homepage
        assert home_page.load_homepage(), "Homepage should load successfully."
        # Step 2: Click 'Find a Store' and then 'Select My Store'
        assert home_page.click_find_a_store(), "Find a Store link should be clickable."
        assert store_locator_popup.wait_for_popup(), "Store Locator popup should be visible."
        assert store_locator_popup.click_select_my_store(), "Select My Store should be clickable."
        # Step 3: Check for location textbox and search button
        assert store_locator_popup.verify_location_textbox_and_search_button(), "Location textbox and Search button should be visible and enabled."

    @pytest.mark.tc2094
    def test_TC2094_enter_location_and_search(self, driver):
        """
        TC ID: 2094 (SCRUM-15408 TS-001 TC-003)
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Enter 'Boston, MA' in the Location textbox.
        4. Click the 'Search for Stores' button.
        """
        home_page = HomePage(driver)
        store_locator_popup = StoreLocatorPopup(driver)
        
        # Step 1: Launch homepage
        assert home_page.load_homepage(), "Homepage should load successfully."
        # Step 2: Click 'Find a Store' and then 'Select My Store'
        assert home_page.click_find_a_store(), "Find a Store link should be clickable."
        assert store_locator_popup.wait_for_popup(), "Store Locator popup should be visible."
        assert store_locator_popup.click_select_my_store(), "Select My Store should be clickable."
        # Step 3: Enter 'Boston, MA' in Location textbox
        assert store_locator_popup.enter_location('Boston, MA'), "Should be able to enter location."
        # Step 4: Click 'Search for Stores' button
        assert store_locator_popup.click_search_for_stores(), "Search for Stores button should be clickable."

    @pytest.mark.tc2095
    def test_TC2095_boston_store_search(self, driver):
        """
        TC ID: 2095 (SCRUM-15408 TS-001 TC-004)
        Validates that searching for ZIP '02108' returns Boston store results.
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Enter '02108' in the Location textbox.
        4. Click the 'Search for Stores' button.
        5. Verify Boston store results are returned.
        """
        home_page = HomePage(driver)
        store_locator_popup = StoreLocatorPopup(driver)
        
        # Step 1: Launch homepage
        assert home_page.load_homepage(), "Homepage should load successfully."
        # Step 2: Click 'Find a Store' and then 'Select My Store'
        assert home_page.click_find_a_store(), "Find a Store link should be clickable."
        assert store_locator_popup.wait_for_popup(), "Store Locator popup should be visible."
        assert store_locator_popup.click_select_my_store(), "Select My Store should be clickable."
        # Step 3: Enter '02108' in Location textbox
        assert store_locator_popup.enter_location('02108'), "Should be able to enter location '02108'."
        # Step 4: Click 'Search for Stores' button
        assert store_locator_popup.click_search_for_stores(), "Search for Stores button should be clickable."
        # Step 5: Verify Boston store results are returned
        assert store_locator_popup.verify_store_results('Boston'), "Boston store results should be returned for ZIP '02108'."

    @pytest.mark.tc2096
    def test_TC2096_nome_alaska_no_store(self, driver):
        """
        TC ID: 2096 (SCRUM-15408 TS-001 TC-005)
        Validates that searching for 'Nome, Alaska' returns 'no stores found' message.
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Enter 'Nome, Alaska' in the Location textbox.
        4. Click the 'Search for Stores' button.
        5. Verify 'no stores found' message is displayed.
        """
        home_page = HomePage(driver)
        store_locator_popup = StoreLocatorPopup(driver)
        
        # Step 1: Launch homepage
        assert home_page.load_homepage(), "Homepage should load successfully."
        # Step 2: Click 'Find a Store' and then 'Select My Store'
        assert home_page.click_find_a_store(), "Find a Store link should be clickable."
        assert store_locator_popup.wait_for_popup(), "Store Locator popup should be visible."
        assert store_locator_popup.click_select_my_store(), "Select My Store should be clickable."
        # Step 3: Enter 'Nome, Alaska' in Location textbox
        assert store_locator_popup.enter_location('Nome, Alaska'), "Should be able to enter location 'Nome, Alaska'."
        # Step 4: Click 'Search for Stores' button
        assert store_locator_popup.click_search_for_stores(), "Search for Stores button should be clickable."
        # Step 5: Verify 'no stores found' message is displayed
        assert store_locator_popup.verify_no_stores_found_message(), "'No stores found' message should be displayed for 'Nome, Alaska'."

    @pytest.mark.tc2097
    def test_TC2097_find_store_search_results(self, driver):
        """
        TC ID: 2097 (SCRUM-15408 TS-001 TC-006)
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Enter 'Boston, MA' in the Location textbox and click 'Search for Stores'.
        4. Assert store results are displayed.
        """
        home_page = HomePage(driver)
        store_locator_popup = StoreLocatorPopup(driver)

        # Step 1: Launch homepage
        assert home_page.load_homepage(), "Homepage should load successfully."
        # Step 2: Click 'Find a Store' and then 'Select My Store'
        assert home_page.click_find_a_store(), "Find a Store link should be clickable."
        assert store_locator_popup.wait_for_popup(), "Store Locator popup should be visible."
        assert store_locator_popup.click_select_my_store(), "Select My Store should be clickable."
        # Step 3: Enter 'Boston, MA' in Location textbox and click search
        assert store_locator_popup.enter_location('Boston, MA'), "Should be able to enter location 'Boston, MA'."
        assert store_locator_popup.click_search_for_stores(), "Search for Stores button should be clickable."
        # Step 4: Assert store results are displayed
        assert store_locator_popup.are_store_results_displayed(), "Store results should be displayed for 'Boston, MA'."

    @pytest.mark.tc2098
    def test_TC2098_boston_store_address_search(self, driver):
        """
        TC ID: 2098 (SCRUM-15408 TS-001 TC-007)
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Enter 'Boston, MA' in the Location textbox and click 'Search for Stores'.
        4. Check results for address '375 Washington Street, Boston, MA 02108'.
        5. Assert store with this address is present.
        """
        home_page = HomePage(driver)
        store_locator_popup = StoreLocatorPopup(driver)

        # Step 1: Launch homepage
        assert home_page.load_homepage(), "Homepage should load successfully."
        # Step 2: Click 'Find a Store' and then 'Select My Store'
        assert home_page.click_find_a_store(), "Find a Store link should be clickable."
        assert store_locator_popup.wait_for_popup(), "Store Locator popup should be visible."
        assert store_locator_popup.click_select_my_store(), "Select My Store should be clickable."
        # Step 3: Enter 'Boston, MA' in Location textbox and click search
        assert store_locator_popup.enter_location('Boston, MA'), "Should be able to enter location 'Boston, MA'."
        assert store_locator_popup.click_search_for_stores(), "Search for Stores button should be clickable."
        # Step 4: Check for the specific address in results
        assert store_locator_popup.is_store_address_present_in_results('375 Washington Street, Boston, MA 02108'), "Store with address '375 Washington Street, Boston, MA 02108' should be present in results."
