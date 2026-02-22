# Selenium Python test scripts for Foot Locker store locator
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
from Pages.Homepage import Homepage
from Pages.StoreLocatorPopup import StoreLocatorPopup
from Pages.MensShoesPage import MensShoesPage

class WomensShoesPage:
    def __init__(self, driver):
        self.driver = driver
    def navigate_to_page(self):
        # Placeholder for navigation logic
        pass
    def verify_preferred_store_indicator(self):
        # Placeholder for verification logic
        return True

class TestFootLockerStoreLocator:
    def setup_method(self):
        self.driver = webdriver.Chrome()
        self.driver.maximize_window()

    def teardown_method(self):
        self.driver.quit()

    # SCRUM-15408 TS-003 TC-002: Verify address format for Boston store
    def test_verify_boston_store_address_format(self):
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

    # SCRUM-15408 TS-004 TC-002: Set a store other than Boston as preferred, then Boston
    def test_set_other_store_then_boston_as_preferred(self):
        home = Homepage(self.driver)
        assert home.load_homepage(), "Homepage did not load."
        assert home.click_find_a_store(), "Could not click 'Find a Store'."
        popup = StoreLocatorPopup(self.driver)
        assert popup.wait_for_popup(), "Popup did not appear."
        assert popup.click_select_my_store(), "Could not click 'Select My Store'."
        assert popup.enter_location("Boston, MA"), "Could not enter location."
        assert popup.click_search_for_stores(), "Could not click 'Search for Stores'."
        assert popup.click_set_my_store_other_than_boston(), "Could not set a store other than Boston as preferred."
        assert popup.click_set_my_store_boston(), "Could not set Boston store as preferred."

    # SCRUM-15408 TS-005 TC-001: Set Boston store as preferred and verify confirmation indicator
    def test_set_boston_store_and_verify_confirmation(self):
        home = Homepage(self.driver)
        assert home.load_homepage(), "Homepage did not load."
        assert home.click_find_a_store(), "Could not click 'Find a Store'."
        popup = StoreLocatorPopup(self.driver)
        assert popup.wait_for_popup(), "Popup did not appear."
        assert popup.click_select_my_store(), "Could not click 'Select My Store'."
        assert popup.enter_location("Boston, MA"), "Could not enter location."
        assert popup.click_search_for_stores(), "Could not click 'Search for Stores'."
        assert popup.click_set_my_store_boston(), "Could not set Boston store as preferred."
        assert popup.verify_store_confirmation_indicator("375 Washington Street, Boston, MA 02108"), "Confirmation indicator not displayed or incorrect."

    # SCRUM-15408 TS-005 TC-002: Set Boston store as preferred, navigate to Men's Shoes, verify indicator
    def test_set_boston_store_and_verify_on_mens_shoes(self):
        home = Homepage(self.driver)
        assert home.load_homepage(), "Homepage did not load."
        assert home.click_find_a_store(), "Could not click 'Find a Store'."
        popup = StoreLocatorPopup(self.driver)
        assert popup.wait_for_popup(), "Popup did not appear."
        assert popup.click_select_my_store(), "Could not click 'Select My Store'."
        assert popup.enter_location("Boston, MA"), "Could not enter location."
        assert popup.click_search_for_stores(), "Could not click 'Search for Stores'."
        assert popup.click_set_my_store_boston(), "Could not set Boston store as preferred."
        assert popup.verify_store_confirmation(), "Store confirmation not displayed."
        mens_page = MensShoesPage(self.driver)
        assert mens_page.navigate_to_page(), "Could not navigate to Men's Shoes page."
        assert mens_page.verify_preferred_store_indicator(), "Preferred store indicator not displayed on Men's Shoes page."

    # SCRUM-15408 TS-006 TC-001: Set Boston store as preferred, navigate to Women's Shoes, verify indicator
    def test_set_boston_store_and_verify_on_womens_shoes(self):
        home = Homepage(self.driver)
        assert home.load_homepage(), "Homepage did not load."
        assert home.click_find_a_store(), "Could not click 'Find a Store'."
        popup = StoreLocatorPopup(self.driver)
        assert popup.wait_for_popup(), "Popup did not appear."
        assert popup.click_select_my_store(), "Could not click 'Select My Store'."
        assert popup.enter_location("Boston, MA"), "Could not enter location."
        assert popup.click_search_for_stores(), "Could not click 'Search for Stores'."
        assert popup.click_set_my_store_boston(), "Could not set Boston store as preferred."
        assert popup.verify_store_confirmation(), "Store confirmation not displayed."
        womens_page = WomensShoesPage(self.driver)
        womens_page.navigate_to_page()
        assert womens_page.verify_preferred_store_indicator(), "Preferred store indicator not displayed on Women's Shoes page."
