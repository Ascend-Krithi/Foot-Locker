import unittest
from selenium import webdriver
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup
from Pages.StoreSelectionPopup import StoreSelectionPopup
from Pages.ConfirmationPage import ConfirmationPage

class TestStoreLocator(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)
        self.driver.get('https://www.footlocker.com/store-locator')
        self.home_page = HomePage(self.driver)
        self.store_locator_popup = StoreLocatorPopup(self.driver)
        self.store_selection_popup = StoreSelectionPopup(self.driver)
        self.confirmation_page = ConfirmationPage(self.driver)

    def tearDown(self):
        self.driver.quit()

    # Existing test methods ...

    def test_2105_search_stores_boston_ma(self):
        """
        Test Case 2105: Launch Foot Locker, open Store Locator, search for 'Boston, MA', verify list of stores is displayed.
        """
        self.home_page.click_find_a_store()
        self.store_locator_popup.enter_location('Boston, MA')
        self.store_locator_popup.click_search_for_stores()
        stores = self.store_locator_popup.get_list_of_stores()
        self.assertTrue(len(stores) > 0, "No stores found for location 'Boston, MA'")

    def test_2106_search_stores_zip_02108(self):
        """
        Test Case 2106: Launch Foot Locker, open Store Locator, search for '02108', verify list of stores is displayed.
        """
        self.home_page.click_find_a_store()
        self.store_locator_popup.enter_location('02108')
        self.store_locator_popup.click_search_for_stores()
        stores = self.store_locator_popup.get_list_of_stores()
        self.assertTrue(len(stores) > 0, "No stores found for location '02108'")

    def test_2107_search_stores_massachusetts(self):
        """
        Test Case 2107: Launch Foot Locker, open Store Locator, search for 'Massachusetts', verify list of stores is displayed.
        """
        self.home_page.click_find_a_store()
        self.store_locator_popup.enter_location('Massachusetts')
        self.store_locator_popup.click_search_for_stores()
        # Assuming get_list_of_stores exists and returns store elements
        stores = self.store_locator_popup.get_list_of_stores()
        self.assertTrue(len(stores) > 0, "No stores found for location 'Massachusetts'")

    def test_2108_search_invalid_city(self):
        """
        Test Case 2108: Launch Foot Locker, open Store Locator, search for 'InvalidCity123', verify error message is displayed.
        """
        self.home_page.click_find_a_store()
        self.store_locator_popup.enter_location('InvalidCity123')
        self.store_locator_popup.click_search_for_stores()
        self.assertTrue(self.store_locator_popup.is_error_message_visible(), "Error message not visible for invalid city.")
        error_text = self.store_locator_popup.get_error_message_text()
        self.assertTrue(error_text != "", "Error message text is empty.")

    def test_2109_use_my_location(self):
        """
        Test Case 2109: Launch Foot Locker, navigate to Store Locator, ensure location permission is enabled, click 'Use My Location', verify stores are displayed.
        """
        self.home_page.go_to_store_locator()
        self.store_locator_popup.click_use_my_location()
        # Assuming get_list_of_stores exists and returns store elements
        stores = self.store_locator_popup.get_list_of_stores()
        self.assertTrue(len(stores) > 0, "No stores found near user's current location.")

    def test_2110_select_store_by_address(self):
        """
        Test Case 2110: Launch Foot Locker, search for 'Boston, MA', select store by address, verify store details popup is displayed.
        """
        self.home_page.go_to_store_locator()
        self.store_locator_popup.enter_location('Boston, MA')
        self.store_locator_popup.click_search_for_stores()
        self.store_selection_popup.select_store_by_address()
        self.assertTrue(self.confirmation_page.is_confirmation_displayed(), "Confirmation not displayed after selecting store.")
