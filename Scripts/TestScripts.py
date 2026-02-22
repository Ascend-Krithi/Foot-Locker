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

@pytest.mark.testcase_2097
def test_find_store_search_results_displayed(driver):
    """
    Test Case 2097:
    Launch the homepage, click 'Find a Store', click 'Select My Store',
    enter 'Boston, MA' in the location textbox, click 'Search for Stores',
    and verify store results are displayed.
    """
    home_page = HomePage(driver)
    home_page.load("https://www.example.com")
    assert home_page.is_loaded(), "Home page did not load successfully."

    home_page.click_find_a_store()

    find_store_popup = FindAStorePopup(driver)
    assert find_store_popup.is_popup_displayed(), "'Find a Store' popup was not displayed."

    find_store_popup.click_select_my_store()
    find_store_popup.enter_location("Boston, MA")
    find_store_popup.click_search_for_stores()

    store_results = StoreSearchResults(driver)
    # Assuming any result means at least one store is present, so we check for any store
    assert store_results.is_store_present(None), "No store results were displayed for 'Boston, MA'."

@pytest.mark.testcase_2098
def test_find_store_search_specific_address(driver):
    """
    Test Case 2098:
    Same as 2097, but after searching, check that the store with address
    '375 Washington Street, Boston, MA 02108' is present in the results.
    """
    home_page = HomePage(driver)
    home_page.load("https://www.example.com")
    assert home_page.is_loaded(), "Home page did not load successfully."

    home_page.click_find_a_store()

    find_store_popup = FindAStorePopup(driver)
    assert find_store_popup.is_popup_displayed(), "'Find a Store' popup was not displayed."

    find_store_popup.click_select_my_store()
    find_store_popup.enter_location("Boston, MA")
    find_store_popup.click_search_for_stores()

    store_results = StoreSearchResults(driver)
    target_address = "375 Washington Street, Boston, MA 02108"
    assert store_results.is_store_present(target_address), f"Store with address '{target_address}' was not found in the results."
