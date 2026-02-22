import pytest
from PageClasses.HomePage import HomePage
from PageClasses.FindAStorePopup import FindAStorePopup
from PageClasses.StoreSearchResults import StoreSearchResults

# Existing test methods...

@pytest.mark.tc2093
def test_tc2093_homepage_find_store_location_textbox(driver):
    """Test Case 2093: Launch homepage, open Find a Store popup, verify location textbox."""
    home_page = HomePage(driver)
    home_page.launch()
    assert home_page.is_loaded(), "Homepage did not load successfully."

    find_store_popup = home_page.click_find_a_store()
    assert find_store_popup.is_open(), "Find a Store popup did not open."

    find_store_popup.click_select_my_store()
    assert find_store_popup.is_location_textbox_visible(), "Location textbox is not visible."
    assert find_store_popup.is_location_textbox_enabled(), "Location textbox is not enabled."

@pytest.mark.tc2094
def test_tc2094_homepage_find_store_boston_search_results(driver):
    """Test Case 2094: Launch homepage, open Find a Store popup, enter Boston, search, verify results."""
    home_page = HomePage(driver)
    home_page.launch()
    assert home_page.is_loaded(), "Homepage did not load successfully."

    find_store_popup = home_page.click_find_a_store()
    assert find_store_popup.is_open(), "Find a Store popup did not open."

    find_store_popup.click_select_my_store()
    assert find_store_popup.is_location_textbox_visible(), "Location textbox is not visible."
    assert find_store_popup.is_location_textbox_enabled(), "Location textbox is not enabled."

    find_store_popup.enter_location("Boston, MA")
    find_store_popup.click_search_for_stores()

    store_results = StoreSearchResults(driver)
    assert store_results.is_results_displayed(), "Store search results are not displayed."
    assert store_results.has_results_for_city("Boston"), "Boston store results are not found."