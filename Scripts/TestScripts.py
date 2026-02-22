# Existing imports and test methods assumed to be present above
import pytest
from PageClasses.HomePage import HomePage
from PageClasses.StoreLocatorPopup import StoreLocatorPopup

# ... (existing test methods remain unchanged)

def test_TC_008_set_boston_store_as_preferred(driver):
    """
    Test Case - SCRUM-15408 TS-001 TC-008:
    Launch homepage, click 'Find a Store', select 'My Store', enter 'Boston, MA', search, set '375 Washington Street, Boston, MA 02108' as preferred, verifying each step.
    """
    home = HomePage(driver)
    home.load()
    assert home.is_loaded(), "Homepage did not load successfully."

    home.click_find_a_store()
    popup = StoreLocatorPopup(driver)
    assert popup.is_displayed(), "Store Locator Popup did not display."

    popup.select_my_store()
    popup.enter_location("Boston, MA")
    popup.click_search()
    assert popup.is_results_displayed(), "Store search results not displayed."

    assert popup.set_preferred_store_by_address("375 Washington Street, Boston, MA 02108"), "Could not set preferred store."
    assert popup.is_preferred_store_confirmation_displayed(), "Preferred store confirmation not displayed."


def test_TC_009_verify_preferred_store_confirmation_and_persistence(driver):
    """
    Test Case - SCRUM-15408 TS-001 TC-009:
    Set the Boston store as preferred, verify UI confirmation indicator and that preferred store is shown across the site.
    """
    home = HomePage(driver)
    home.load()
    assert home.is_loaded(), "Homepage did not load successfully."

    home.click_find_a_store()
    popup = StoreLocatorPopup(driver)
    assert popup.is_displayed(), "Store Locator Popup did not display."

    popup.select_my_store()
    popup.enter_location("Boston, MA")
    popup.click_search()
    assert popup.is_results_displayed(), "Store search results not displayed."

    assert popup.set_preferred_store_by_address("375 Washington Street, Boston, MA 02108"), "Could not set preferred store."
    assert popup.is_preferred_store_confirmation_displayed(), "Preferred store confirmation not displayed."

    # Verify UI confirmation indicator on the homepage
    home.close_store_locator_popup_if_open()
    assert home.is_store_indicator_displayed(), "Preferred store indicator is not displayed on the homepage."
    assert home.get_preferred_store_name() == "375 Washington Street, Boston, MA 02108", "Preferred store name not shown correctly across the site."
