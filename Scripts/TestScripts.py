
Automated tests for Foot Locker Store Locator using Selenium and pytest.
"""
import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from Pages.HomePage import HomePage
from Pages.FindStorePopup import FindStorePopup
from Pages.Header import Header
from Pages.MensShoesPage import MensShoesPage
from Pages.WomensShoesPage import WomensShoesPage

@pytest.fixture(scope='function')
def driver():
    driver = webdriver.Chrome()
    yield driver
    driver.quit()

class TestStoreLocator:
    def test_search_valid_location(self, driver):
        """Test searching for stores in a valid location (Boston, MA)"""
        # Existing logic
        pass

    def test_search_invalid_location(self, driver):
        """Test searching for stores in an invalid location (Antarctica)"""
        # Existing logic
        pass

    def test_store_locator_popup_and_address(self, driver):
        """Test Case - SCRUM-15408 TS-003 TC-001: Verify store locator popup and address match"""
        # Existing logic
        pass

    def test_set_my_store(self, driver):
        """Test Case - SCRUM-15408 TS-004 TC-001: Set '375 Washington Street, Boston, MA 02108' as preferred store"""
        # Existing logic
        pass

    def test_find_a_store_popup_and_select_link(self, driver):
        ...
    def test_select_my_store_opens_locator_popup(self, driver):
        ...
    def test_TC_2075_store_locator_search_boston(self, driver):
        ...
    def test_TC_2076_verify_store_address_boston(self, driver):
        ...
    def test_TC_2085_verify_store_address_format(self, driver):
        ...
    def test_TC_2086_set_my_store_boston(self, driver):
        ...
    def test_TC_2087_set_my_store_other_then_boston(self, driver):
        ...
    def test_TC_2088_set_my_store_boston_and_verify_confirmation(self, driver):
        ...

    def test_TC_15408_TS_005_TC_002_set_preferred_store_and_verify_header_mens(self, driver):
        """
        Test Case - SCRUM-15408 TS-005 TC-002:
        Verifies that after setting '375 Washington Street, Boston, MA 02108' as the preferred store,
        the store appears in the header indicator when navigating to Men's Shoes page.
        """
        home_page = HomePage(driver)
        home_page.load()
        # Click 'Find a Store'
        home_page.click_find_a_store()
        # Click 'Select My Store'
        home_page.click_select_my_store()
        find_store_popup = FindStorePopup(driver)
        find_store_popup.enter_location('Boston, MA')
        find_store_popup.click_search()
        find_store_popup.set_my_store()
        mens_shoes_page = MensShoesPage(driver)
        mens_shoes_page.load()
        header = Header(driver)
        preferred_store = header.get_selected_store()
        assert '375 Washington Street, Boston, MA 02108' in preferred_store, \
            f"Expected preferred store to be set in header, got: {preferred_store}"

    def test_TC_15408_TS_006_TC_001_set_preferred_store_and_verify_header_womens(self, driver):
        """
        Test Case - SCRUM-15408 TS-006 TC-001:
        Verifies that after setting '375 Washington Street, Boston, MA 02108' as the preferred store,
        the store appears in the header indicator when navigating to Women's Shoes page.
        """
        home_page = HomePage(driver)
        home_page.load()
        # Click 'Find a Store'
        home_page.click_find_a_store()
        # Click 'Select My Store'
        home_page.click_select_my_store()
        find_store_popup = FindStorePopup(driver)
        find_store_popup.enter_location('Boston, MA')
        find_store_popup.click_search()
        find_store_popup.set_my_store()
        womens_shoes_page = WomensShoesPage(driver)
        womens_shoes_page.load()
        header = Header(driver)
        preferred_store = header.get_selected_store()
        assert '375 Washington Street, Boston, MA 02108' in preferred_store, \
            f"Expected preferred store to be set in header, got: {preferred_store}"
