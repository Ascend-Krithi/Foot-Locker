import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from Pages.HomePage import HomePage
from Pages.FindAStorePopup import FindAStorePopup
from Pages.StoreSearchResults import StoreSearchResults

@pytest.fixture(scope='function')
def driver():
    driver = webdriver.Chrome()
    yield driver
    driver.quit()

class TestStoreLocator:
    # Existing methods...

    def test_TC_2093_location_textbox_enabled(self, driver):
        """
        Test Case - SCRUM-15408 TS-001 TC-002
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Check for the presence and enabled state of the Location textbox.
        """
        url = "https://www.footlocker.com/"
        home_page = HomePage(driver)
        home_page.load(url)
        assert home_page.is_loaded(), "Homepage is not displayed."
        home_page.click_find_a_store()
        find_store_popup = FindAStorePopup(driver)
        find_store_popup.click_select_my_store()
        assert find_store_popup.is_popup_displayed(), "Store Locator Popup is not displayed."
        assert find_store_popup.is_location_textbox_enabled(), "Location textbox is not visible and enabled for user input."

    def test_TC_2094_search_boston_store(self, driver):
        """
        Test Case - SCRUM-15408 TS-001 TC-003
        Steps:
        1. Launch the Foot Locker website and navigate to the homepage.
        2. Click 'Find a Store' and then 'Select My Store'.
        3. Enter 'Boston, MA' in the Location textbox.
        4. Click the 'Search for Stores' button.
        5. Store results in or near Boston are displayed.
        """
        url = "https://www.footlocker.com/"
        home_page = HomePage(driver)
        home_page.load(url)
        assert home_page.is_loaded(), "Homepage is not displayed."
        home_page.click_find_a_store()
        find_store_popup = FindAStorePopup(driver)
        find_store_popup.click_select_my_store()
        assert find_store_popup.is_popup_displayed(), "Store Locator Popup is not displayed."
        find_store_popup.enter_location("Boston, MA")
        find_store_popup.click_search_for_stores()
        # After search, validate results are displayed
        # Optionally, instantiate StoreSearchResults and check results
        store_results = StoreSearchResults(driver)
        # Here we check for at least one result for Boston
        assert driver.find_elements(By.XPATH, "//div[contains(text(), 'Boston')]") != [], "Store results in or near Boston are not displayed."
