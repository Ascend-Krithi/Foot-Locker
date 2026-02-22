import pytest
from PageClasses.HomePage import HomePage
from PageClasses.FindAStorePopup import FindAStorePopup
from PageClasses.StoreSearchResults import StoreSearchResults

# Existing test methods...

@pytest.mark.tc2093
def test_tc2093_homepage_find_store_location_textbox(driver):
    ...
# (full content provided in previous step)


@pytest.mark.tc008
def test_tc008_set_preferred_store_boston(driver):
    """
    TC-008: Launch homepage, open Find a Store, select My Store, search for 'Boston, MA',
    set '375 Washington Street, Boston, MA 02108' as preferred store, and verify.
    """
    homepage = HomePage(driver)
    homepage.go_to_homepage("https://www.footlocker.com/")
    # Optionally, assert homepage loaded
    
    homepage.click_find_a_store()
    popup = FindAStorePopup(driver)
    popup.click_select_my_store()
    assert popup.is_displayed(), "Find A Store popup did not display"
    
    popup.enter_location_and_search("Boston, MA")
    results = StoreSearchResults(driver)
    preferred_address = "375 Washington Street, Boston, MA 02108"
    results.set_my_store_by_address(preferred_address)
    
    assert results.is_store_set_confirmation_displayed(preferred_address), (
        f"Preferred store confirmation not displayed for {preferred_address}"
    )

@pytest.mark.tc009
def test_tc009_verify_preferred_store_persistence(driver):
    """
    TC-009: Set '375 Washington Street, Boston, MA 02108' as 'My Store',
    verify confirmation indicator and store appears consistently.
    """
    homepage = HomePage(driver)
    homepage.go_to_homepage("https://www.footlocker.com/")
    homepage.click_find_a_store()
    popup = FindAStorePopup(driver)
    popup.click_select_my_store()
    assert popup.is_displayed(), "Find A Store popup did not display"
    
    popup.enter_location_and_search("Boston, MA")
    results = StoreSearchResults(driver)
    preferred_address = "375 Washington Street, Boston, MA 02108"
    results.set_my_store_by_address(preferred_address)
    
    # Verify confirmation indicator
    assert results.is_store_set_confirmation_displayed(preferred_address), (
        f"Confirmation indicator not displayed for {preferred_address}"
    )
    # Optionally, re-open popup or check homepage for persistence
    homepage.go_to_homepage("https://www.footlocker.com/")
    homepage.click_find_a_store()
    popup.click_select_my_store()
    assert popup.is_displayed(), "Find A Store popup did not display on revisit"
    # Check again for persistence
    assert results.is_store_set_confirmation_displayed(preferred_address), (
        f"Preferred store not persisted for {preferred_address} after revisit"
    )

@pytest.mark.tc2103
def test_tc2103_verify_find_a_store_popup_from_homepage(driver):
    """
    TC-2103: Launch the Foot Locker website, verify homepage is displayed,
    verify 'Find a Store' link is visible, click it, and verify the Store Locator popup appears.
    """
    homepage = HomePage(driver)
    homepage.go_to_homepage("https://www.footlocker.com/")
    assert homepage.is_homepage_displayed(), "Homepage did not load as expected"
    homepage.click_find_a_store()
    popup = FindAStorePopup(driver)
    assert popup.is_displayed(), "Store Locator popup did not appear after clicking 'Find a Store'"

@pytest.mark.tc2104
def test_tc2104_verify_store_locator_popup_contents(driver):
    """
    TC-2104: Launch the Foot Locker website, click 'Find a Store',
    verify the Store Locator popup appears, and that the popup displays the expected message and link.
    """
    homepage = HomePage(driver)
    homepage.go_to_homepage("https://www.footlocker.com/")
    homepage.click_find_a_store()
    popup = FindAStorePopup(driver)
    assert popup.is_displayed(), "Store Locator popup did not appear after clicking 'Find a Store'"
    expected_message = "Choose a preferred store to make shopping easier"
    assert popup.is_popup_message_displayed(expected_message), (
        f"Popup message '{expected_message}' not displayed"
    )
    assert popup.is_select_my_store_link_visible(), "'Select My Store' link is not visible in the popup"
