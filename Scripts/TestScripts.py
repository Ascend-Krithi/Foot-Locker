# TestScripts.py
"""
Automated tests for Foot Locker Store Locator using Selenium and pytest.
"""
import pytest
from selenium import webdriver
from src.pages.FootLockerHomePage import FootLockerHomePage
from src.pages.StoreLocatorPage import StoreLocatorPage

@pytest.fixture(scope='function')
def driver():
    driver = webdriver.Chrome()
    yield driver
    driver.quit()

class TestStoreLocator:
    def test_search_valid_location(self, driver):
        """Test searching for stores in a valid location (Boston, MA)"""
        home_page = FootLockerHomePage(driver)
        home_page.load()
        home_page.open_store_locator()
        locator_page = StoreLocatorPage(driver)
        locator_page.enter_location('Boston, MA')
        locator_page.search_for_stores()
        assert locator_page.is_results_displayed(), "Store results should be displayed for Boston, MA"

    def test_search_invalid_location(self, driver):
        """Test searching for stores in an invalid location (Antarctica)"""
        home_page = FootLockerHomePage(driver)
        home_page.load()
        home_page.open_store_locator()
        locator_page = StoreLocatorPage(driver)
        locator_page.enter_location('Antarctica')
        locator_page.search_for_stores()
        assert locator_page.is_no_results_message_displayed(), "No stores found message should be displayed for Antarctica"

    def test_store_locator_popup_and_address(self, driver):
        """Test Case - SCRUM-15408 TS-003 TC-001: Verify store locator popup and address match"""
        home_page = FootLockerHomePage(driver)
        home_page.load()
        home_page.open_store_locator()
        locator_page = StoreLocatorPage(driver)
        locator_page.enter_location('Boston, MA')
        locator_page.search_for_stores()
        assert locator_page.is_results_displayed(), "Store results should be displayed for Boston, MA"
        assert locator_page.is_store_address_present('375 Washington Street, Boston, MA 02108'), "Store address should match exactly"

    def test_set_my_store(self, driver):
        """Test Case - SCRUM-15408 TS-004 TC-001: Set '375 Washington Street, Boston, MA 02108' as preferred store"""
        home_page = FootLockerHomePage(driver)
        home_page.load()
        home_page.open_store_locator()
        locator_page = StoreLocatorPage(driver)
        locator_page.enter_location('Boston, MA')
        locator_page.search_for_stores()
        locator_page.set_my_store('375 Washington Street, Boston, MA 02108')
        assert locator_page.is_preferred_store_set('375 Washington Street, Boston, MA 02108'), "Selected store should be saved as user's preferred store"
