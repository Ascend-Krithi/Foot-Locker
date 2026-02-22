# Existing imports and code remain unchanged
import pytest
from selenium.webdriver.common.by import By
from Pages.HomePage import HomePage
from Pages.FindAStorePopup import FindAStorePopup
from Pages.StoreSearchResults import StoreSearchResults

# Existing test classes and functions... (preserved)

# Test case 2093: Verify homepage loads, 'Find a Store' and 'Select My Store' are clicked, the store locator popup appears, and the location textbox is visible and enabled.
def test_TC2093_find_a_store_popup_location_box_visible_enabled(driver):
    """
    Test Case 2093:
    1. Verify homepage loads.
    2. Click 'Find a Store'.
    3. Click 'Select My Store'.
    4. Verify store locator popup appears.
    5. Verify location textbox is visible and enabled.
    """
    home_page = HomePage(driver)
    home_page.load()  # Assuming a load() method exists, else driver.get(url)
    assert home_page.is_loaded(), "Homepage did not load successfully."

    home_page.click_find_a_store()
    home_page.click_select_my_store()

    store_popup = FindAStorePopup(driver)
    assert store_popup.is_displayed(), "Store locator popup did not appear."
    assert store_popup.location_textbox_is_visible(), "Location textbox is not visible."
    assert store_popup.location_textbox_is_enabled(), "Location textbox is not enabled."

# Test case 2094: Repeat above, then enter 'Boston, MA' and search

def test_TC2094_find_a_store_search_boston(driver):
    """
    Test Case 2094:
    1. Verify homepage loads.
    2. Click 'Find a Store'.
    3. Click 'Select My Store'.
    4. Verify store locator popup appears.
    5. Verify location textbox is visible and enabled.
    6. Enter 'Boston, MA' in the location textbox.
    7. Click 'Search for Stores'.
    """
    home_page = HomePage(driver)
    home_page.load()
    assert home_page.is_loaded(), "Homepage did not load successfully."

    home_page.click_find_a_store()
    home_page.click_select_my_store()

    store_popup = FindAStorePopup(driver)
    assert store_popup.is_displayed(), "Store locator popup did not appear."
    assert store_popup.location_textbox_is_visible(), "Location textbox is not visible."
    assert store_popup.location_textbox_is_enabled(), "Location textbox is not enabled."

    store_popup.enter_location("Boston, MA")
    store_popup.click_search_for_stores()

    results_page = StoreSearchResults(driver)
    assert results_page.is_loaded(), "Store search results page did not load."
