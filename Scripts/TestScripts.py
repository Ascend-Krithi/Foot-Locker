# Selenium Python test scripts for Foot Locker store locator
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
from Pages.Homepage import Homepage
from Pages.StoreLocatorPopup import StoreLocatorPopup

class TestFootLockerStoreLocator:
    def setup_method(self):
        self.driver = webdriver.Chrome()
        self.driver.maximize_window()

    def teardown_method(self):
        self.driver.quit()

    # SCRUM-15408 TS-003 TC-002: Verify address format for Boston store
    def test_verify_boston_store_address_format(self):
        """
        SCRUM-15408 TS-003 TC-002
        Verifies the address format for '375 Washington Street, Boston, MA 02108' after searching in Boston, MA.
        """
        home = Homepage(self.driver)
        home.load()
        home.click_find_a_store()

        popup = StoreLocatorPopup(self.driver)
        popup.click_select_my_store()
        popup.enter_location_textbox("Boston, MA")
        popup.click_search_for_stores()
        popup.select_store_by_address("375 Washington Street, Boston, MA 02108")
        assert popup.verify_store_address_format("375 Washington Street, Boston, MA 02108"), "Store address format is incorrect."

    # SCRUM-15408 TS-004 TC-001: Set Boston store as preferred and verify
    def test_set_boston_store_as_preferred(self):
        """
        SCRUM-15408 TS-004 TC-001
        Sets '375 Washington Street, Boston, MA 02108' as preferred store and verifies it is saved and displayed.
        """
        home = Homepage(self.driver)
        home.load()
        home.click_find_a_store()

        popup = StoreLocatorPopup(self.driver)
        popup.click_select_my_store()
        popup.enter_location_textbox("Boston, MA")
        popup.click_search_for_stores()
        popup.select_store_by_address("375 Washington Street, Boston, MA 02108")
        popup.click_set_my_store("375 Washington Street, Boston, MA 02108")
        assert popup.verify_preferred_store_saved("375 Washington Street, Boston, MA 02108"), "Preferred store was not saved or displayed correctly."
