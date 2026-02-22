import pytest
from PageClasses.HomePage import HomePage
from PageClasses.FindAStorePopup import FindAStorePopup
from PageClasses.StoreSearchResults import StoreSearchResults

# Existing test methods...

@pytest.mark.tc2093
def test_tc2093_homepage_find_store_location_textbox(driver):
    # ... existing implementation ...
    pass

@pytest.mark.tc2094
def test_tc2094_homepage_find_store_boston_search_results(driver):
    # ... existing implementation ...
    pass

@pytest.mark.tc2095
def test_tc2095_homepage_find_store_boston_results(driver):
    """
    TestCase 2095:
    1. Launch homepage (https://www.footlocker.com/)
    2. Click 'Find a Store' and 'Select My Store'
    3. Enter '02108' in Location textbox
    4. Click 'Search for Stores' button
    5. Expect store results in or near Boston
    """
    home_page = HomePage(driver)
    home_page.load()

    home_page.click_find_a_store()
    find_store_popup = FindAStorePopup(driver)
    assert find_store_popup.is_displayed(), "Find A Store popup did not display"

    find_store_popup.click_select_my_store()
    find_store_popup.enter_location("02108")
    find_store_popup.click_search()

    results_page = StoreSearchResults(driver)
    # You may want to parameterize the address or use a known Boston address from test data
    boston_address = "375 Washington St, Boston, MA 02108"  # Example address
    assert results_page.is_store_result_displayed(boston_address), f"Store with address '{boston_address}' not found in results"

@pytest.mark.tc2096
def test_tc2096_homepage_find_store_nome_alaska_no_results(driver):
    """
    TestCase 2096:
    1. Launch homepage (https://www.footlocker.com/)
    2. Click 'Find a Store' and 'Select My Store'
    3. Enter 'Nome, Alaska' in Location textbox
    4. Click 'Search for Stores' button
    5. Expect 'No stores found near this location' message
    """
    home_page = HomePage(driver)
    home_page.load()

    home_page.click_find_a_store()
    find_store_popup = FindAStorePopup(driver)
    assert find_store_popup.is_displayed(), "Find A Store popup did not display"

    find_store_popup.click_select_my_store()
    find_store_popup.enter_location("Nome, Alaska")
    find_store_popup.click_search()

    results_page = StoreSearchResults(driver)
    assert results_page.is_no_stores_found_displayed(), "'No stores found near this location' message was not displayed"
