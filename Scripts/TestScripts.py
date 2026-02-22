# Existing imports and test methods assumed to be present above
import pytest
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup

# ... (existing test methods remain unchanged)

def test_TC_008_set_boston_store_as_preferred(driver):
    """
    Test Case - SCRUM-15408 TS-001 TC-008:
    Launch homepage, click 'Find a Store', select 'My Store', enter 'Boston, MA', search, set '375 Washington Street, Boston, MA 02108' as preferred, verifying each step.
    """
    home = HomePage(driver)
    assert home.load_homepage(), "Homepage did not load successfully."

    assert home.click_find_a_store(), "Failed to click 'Find a Store'."
    popup = StoreLocatorPopup(driver)
    assert popup.wait_for_popup(), "Store Locator Popup did not display."

    assert popup.is_select_my_store_link_visible(), "'Select My Store' not visible."
    assert popup.click_select_my_store(), "Failed to click 'Select My Store'."
    assert popup.verify_location_textbox_and_search_button(), "Location textbox or search button not visible."
    assert popup.enter_location("Boston, MA"), "Failed to enter location."
    assert popup.click_search_for_stores(), "Failed to click 'Search for Stores'."
    assert popup.are_store_results_displayed(), "Store search results not displayed."
    assert popup.is_store_address_present_in_results("375 Washington Street, Boston, MA 02108"), "Store address not found in results."
    assert popup.click_set_my_store(), "Failed to click 'Set My Store'."
    assert popup.verify_preferred_store_saved("375 Washington Street, Boston, MA 02108"), "Preferred store confirmation not displayed."


def test_TC_009_verify_preferred_store_confirmation_and_persistence(driver):
    """
    Test Case - SCRUM-15408 TS-001 TC-009:
    Set the Boston store as preferred, verify UI confirmation indicator and that preferred store is shown across the site.
    """
    home = HomePage(driver)
    assert home.load_homepage(), "Homepage did not load successfully."

    assert home.click_find_a_store(), "Failed to click 'Find a Store'."
    popup = StoreLocatorPopup(driver)
    assert popup.wait_for_popup(), "Store Locator Popup did not display."

    assert popup.is_select_my_store_link_visible(), "'Select My Store' not visible."
    assert popup.click_select_my_store(), "Failed to click 'Select My Store'."
    assert popup.verify_location_textbox_and_search_button(), "Location textbox or search button not visible."
    assert popup.enter_location("Boston, MA"), "Failed to enter location."
    assert popup.click_search_for_stores(), "Failed to click 'Search for Stores'."
    assert popup.are_store_results_displayed(), "Store search results not displayed."
    assert popup.is_store_address_present_in_results("375 Washington Street, Boston, MA 02108"), "Store address not found in results."
    assert popup.click_set_my_store(), "Failed to click 'Set My Store'."
    assert popup.verify_preferred_store_saved("375 Washington Street, Boston, MA 02108"), "Preferred store confirmation not displayed."
    # Step 2: Verify UI confirmation indicator on other pages
    assert popup.verify_store_indicator_on_other_page("375 Washington Street, Boston, MA 02108"), "Preferred store indicator not shown on other pages."


def test_TC_001_store_locator_search_city(driver):
    """
    Test Case - SCRUM-15408 TS-SL-002 TC-001:
    1. Launch the Foot Locker website and navigate to the Store Locator page.
    2. Enter 'Boston, MA' in the Location textbox.
    3. Click on the 'Search for Stores' button.
    4. Verify list of stores in or near Boston is displayed.
    """
    home = HomePage(driver)
    assert home.go_to_store_locator_page(), "Store Locator page did not load successfully."
    popup = StoreLocatorPopup(driver)
    assert popup.verify_location_textbox_and_search_button(), "Location textbox or search button not visible."
    assert popup.enter_location("Boston, MA"), "Failed to enter 'Boston, MA' in the location textbox."
    assert popup.click_search_for_stores(), "Failed to click 'Search for Stores'."
    assert popup.are_store_results_displayed(), "Store search results not displayed for 'Boston, MA'."


def test_TC_002_store_locator_search_zip(driver):
    """
    Test Case - SCRUM-15408 TS-SL-003 TC-001:
    1. Launch the Foot Locker website and navigate to the Store Locator page.
    2. Enter '02108' in the Location textbox.
    3. Click on the 'Search for Stores' button.
    4. Verify list of stores in or near ZIP code 02108 is displayed.
    """
    home = HomePage(driver)
    assert home.go_to_store_locator_page(), "Store Locator page did not load successfully."
    popup = StoreLocatorPopup(driver)
    assert popup.verify_location_textbox_and_search_button(), "Location textbox or search button not visible."
    assert popup.enter_location("02108"), "Failed to enter '02108' in the location textbox."
    assert popup.click_search_for_stores(), "Failed to click 'Search for Stores'."
    assert popup.are_store_results_displayed(), "Store search results not displayed for '02108'."

# New test methods appended below

def test_TC_2103_homepage_and_find_store_popup(driver):
    """
    Test Case - SCRUM-15408 TS-SL-001 TC-001:
    1. Launch the Foot Locker website in a browser. [Test Data- URL: https://www.footlocker.com/] [Acceptance Criteria- AC1]
    2. Locate the 'Find a Store' link in the header. [Acceptance Criteria- AC1]
    3. Click on the 'Find a Store' link. [Acceptance Criteria- AC1]
    """
    home = HomePage(driver)
    assert home.load_homepage(), "Home Page did not load successfully."
    assert home.click_find_a_store(), "Failed to click 'Find a Store'."
    popup = StoreLocatorPopup(driver)
    assert popup.wait_for_popup(), "Store Locator popup did not appear."
    assert popup.is_select_my_store_link_visible(), "'Find a Store' link is not visible in popup."


def test_TC_2104_popup_message_and_select_my_store(driver):
    """
    Test Case - SCRUM-15408 TS-SL-001 TC-002:
    1. Launch the Foot Locker website in a browser. [Test Data- URL: https://www.footlocker.com/] [Acceptance Criteria- AC1]
    2. Click on the 'Find a Store' link in the header. [Acceptance Criteria- AC1]
    3. Observe the popup message and available links. [Acceptance Criteria- AC1]
    """
    home = HomePage(driver)
    assert home.load_homepage(), "Home Page did not load successfully."
    assert home.click_find_a_store(), "Failed to click 'Find a Store'."
    popup = StoreLocatorPopup(driver)
    assert popup.wait_for_popup(), "Store Locator popup did not appear."
    expected_message = "Choose a preferred store to make shopping easier"
    assert popup.verify_popup_message(expected_message), f"Popup message '{expected_message}' not found."
    assert popup.is_select_my_store_link_visible(), "'Select My Store' link is not visible in popup."
