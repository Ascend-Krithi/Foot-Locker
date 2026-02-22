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

@pytest.mark.tc2095
def test_tc2095_homepage_find_store_boston_zip(driver):
    """Test Case 2095: Launch homepage, open Find a Store popup, enter ZIP 02108, search, verify Boston results."""
    home_page = HomePage(driver)
    home_page.load("https://www.footlocker.com/")
    assert home_page.is_loaded(), "Homepage did not load successfully."

    home_page.click_find_a_store()
    find_store_popup = FindAStorePopup(driver)
    assert find_store_popup.is_popup_displayed(), "Store Locator Popup is not displayed."

    find_store_popup.click_select_my_store()
    find_store_popup.enter_location("02108")
    find_store_popup.click_search_for_stores()

    store_results = StoreSearchResults(driver)
    assert not store_results.is_no_stores_found(), "'No stores found' message displayed unexpectedly."
    # Optionally check for Boston in results if available

@pytest.mark.tc2096
def test_tc2096_homepage_find_store_nome_alaska(driver):
    """Test Case 2096: Launch homepage, open Find a Store popup, enter Nome, Alaska, search, verify 'No stores found'."""
    home_page = HomePage(driver)
    home_page.load("https://www.footlocker.com/")
    assert home_page.is_loaded(), "Homepage did not load successfully."

    home_page.click_find_a_store()
    find_store_popup = FindAStorePopup(driver)
    assert find_store_popup.is_popup_displayed(), "Store Locator Popup is not displayed."

    find_store_popup.click_select_my_store()
    find_store_popup.enter_location("Nome, Alaska")
    find_store_popup.click_search_for_stores()

    store_results = StoreSearchResults(driver)
    assert store_results.is_no_stores_found(), "'No stores found' message not displayed as expected."

@pytest.mark.tc2097
def test_tc2097_find_store_boston_ma(driver):
    """Test Case 2097: Launch homepage, click 'Find a Store', select 'My Store', enter 'Boston, MA', search, verify store results."""
    home_page = HomePage(driver)
    home_page.load("https://www.footlocker.com/")
    assert home_page.is_loaded(), "Homepage did not load successfully."

    home_page.click_find_a_store()
    find_store_popup = FindAStorePopup(driver)
    assert find_store_popup.is_popup_displayed(), "Store Locator Popup is not displayed."

    find_store_popup.click_select_my_store()
    find_store_popup.enter_location("Boston, MA")
    find_store_popup.click_search_for_stores()

    store_results = StoreSearchResults(driver)
    # Assuming is_results_displayed or similar is implemented
    assert store_results.is_store_present_in_results("Boston, MA"), "Store results for Boston, MA not found."

@pytest.mark.tc2098
def test_tc2098_find_store_boston_ma_with_address(driver):
    """Test Case 2098: Launch homepage, click 'Find a Store', select 'My Store', enter 'Boston, MA', search, verify specific store address in results."""
    home_page = HomePage(driver)
    home_page.load("https://www.footlocker.com/")
    assert home_page.is_loaded(), "Homepage did not load successfully."

    home_page.click_find_a_store()
    find_store_popup = FindAStorePopup(driver)
    assert find_store_popup.is_popup_displayed(), "Store Locator Popup is not displayed."

    find_store_popup.click_select_my_store()
    find_store_popup.enter_location("Boston, MA")
    find_store_popup.click_search_for_stores()

    store_results = StoreSearchResults(driver)
    assert store_results.is_store_present_in_results("375 Washington Street, Boston, MA 02108"), "Store with address '375 Washington Street, Boston, MA 02108' not found in results."
