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
