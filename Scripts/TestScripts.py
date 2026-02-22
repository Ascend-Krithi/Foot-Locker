# Placeholder TestScripts.py

# TODO: Add test methods for testCaseId 2015, 2016, etc.

def test_case_2017():
    ... (existing methods omitted for brevity)

def test_case_2101(driver):
    """
    TestCaseId: 2101
    Description: Test Case - SCRUM-15408 TS-001 TC-010
    Steps:
        1. Set the store at '375 Washington Street, Boston, MA 02108' as 'My Store'.
        2. Navigate to another page (e.g., Men's Shoes).
        3. Return to the homepage.
        4. Click 'Find a Store' and 'Select My Store' again.
        5. Verify the previously selected store is still set as 'My Store'.
    """
    from Pages.HomepagePage import HomepagePage
    from Pages.MensShoesPage import MensShoesPage
    homepage = HomepagePage(driver)
    mens_shoes_page = MensShoesPage(driver)
    homepage.set_preferred_store('375 Washington Street, Boston, MA 02108')
    homepage.navigate_to_mens_shoes()
    assert mens_shoes_page.verify_on_mens_shoes_page(), "Men's Shoes page not displayed."
    driver.get('https://www.footlocker.com/')
    homepage.verify_store_persistence('375 Washington Street, Boston, MA 02108')

# ... (other methods omitted for brevity) ...

def test_case_2115(driver):
    """
    TestCaseId: 2115
    Description: Simulate store locator API being unavailable, search for 'Boston, MA', and verify user-friendly error message is displayed with no results shown.
    Steps:
        1. Simulate store locator API unavailability.
        2. Attempt a store search for 'Boston, MA'.
        3. Assert a user-friendly error message is displayed.
        4. Assert no results are shown.
    """
    from Pages.StoreLocatorPage import StoreLocatorPage
    store_locator_page = StoreLocatorPage(driver)
    # Step 1: Simulate API unavailability
    store_locator_page.simulate_api_unavailability()
    # Step 2: Attempt search with API failure
    store_locator_page.search_store_with_api_failure('Boston, MA')
    # Step 3: Assert user-friendly error message is displayed
    error_message = store_locator_page.get_error_message()
    assert error_message is not None and error_message != "", "Error message should be displayed when API is unavailable."
    expected_error = "Sorry, we are unable to retrieve store results at this time. Please try again later."
    assert expected_error in error_message, f"Expected user-friendly error message not shown. Actual: '{error_message}'"
    # Step 4: Assert no results are shown
    results = store_locator_page.get_results()
    assert results == [] or results is None, "No store results should be shown when API is unavailable."
