import unittest
from selenium import webdriver
from Pages.Homepage import Homepage
from Pages.Header import Header
from Pages.FindAStorePopup import FindAStorePopup
from Pages.StoreSelectionPopup import StoreSelectionPopup

class TestFootLockerStoreLocator(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.homepage = Homepage(self.driver)
        self.header = Header(self.driver)
        self.find_store_popup = FindAStorePopup(self.driver)
        self.store_selection_popup = StoreSelectionPopup(self.driver)

    def tearDown(self):
        self.driver.quit()

    def test_TC_2011_find_a_store_popup(self):
        loaded = self.homepage.load()
        self.assertTrue(loaded, "Foot Locker homepage did not load successfully.")
        clicked = self.header.click_find_a_store()
        self.assertTrue(clicked, "Failed to click 'Find a Store' link.")
        popup_visible = self.find_store_popup.is_popup_visible()
        self.assertTrue(popup_visible, "Find a Store popup did not appear.")
        message_ok = self.find_store_popup.verify_message()
        self.assertTrue(message_ok, "Popup message not as expected.")

    def test_TC_2012_select_my_store_popup(self):
        loaded = self.homepage.load()
        self.assertTrue(loaded, "Foot Locker homepage did not load successfully.")
        clicked = self.header.click_find_a_store()
        self.assertTrue(clicked, "Failed to click 'Find a Store' link.")
        popup_visible = self.find_store_popup.is_popup_visible()
        self.assertTrue(popup_visible, "Find a Store popup did not appear.")
        select_store_clicked = self.find_store_popup.click_select_my_store()
        self.assertTrue(select_store_clicked, "Failed to click 'Select My Store' link.")
        store_popup_visible = self.store_selection_popup.is_popup_window_visible()
        self.assertTrue(store_popup_visible, "Store Selection popup window did not appear.")

    def test_TC_2013_search_store_in_boston(self):
        loaded = self.homepage.load()
        self.assertTrue(loaded, "Foot Locker homepage did not load successfully.")
        clicked = self.header.click_find_a_store()
        self.assertTrue(clicked, "Failed to click 'Find a Store' link.")
        select_store_clicked = self.find_store_popup.click_select_my_store()
        self.assertTrue(select_store_clicked, "Failed to click 'Select My Store' link.")
        store_popup_visible = self.store_selection_popup.is_popup_window_visible()
        self.assertTrue(store_popup_visible, "Store Locator popup did not appear.")
        location_entered = self.store_selection_popup.enter_location('Boston, MA')
        self.assertTrue(location_entered, "Failed to enter 'Boston, MA' in the Location textbox.")
        search_clicked = self.store_selection_popup.click_search()
        self.assertTrue(search_clicked, "Failed to click 'Search for Stores' button.")
        results_displayed = self.store_selection_popup.verify_search_results('Boston')
        self.assertTrue(results_displayed, "Search results for 'Boston' not displayed as expected.")

    def test_TC_2014_search_store_in_antarctica(self):
        loaded = self.homepage.load()
        self.assertTrue(loaded, "Foot Locker homepage did not load successfully.")
        clicked = self.header.click_find_a_store()
        self.assertTrue(clicked, "Failed to click 'Find a Store' link.")
        select_store_clicked = self.find_store_popup.click_select_my_store()
        self.assertTrue(select_store_clicked, "Failed to click 'Select My Store' link.")
        store_popup_visible = self.store_selection_popup.is_popup_window_visible()
        self.assertTrue(store_popup_visible, "Store Locator popup did not appear.")
        location_entered = self.store_selection_popup.enter_location('Antarctica')
        self.assertTrue(location_entered, "Failed to enter 'Antarctica' in the Location textbox.")
        search_clicked = self.store_selection_popup.click_search()
        self.assertTrue(search_clicked, "Failed to click 'Search for Stores' button.")
        no_stores_message = self.store_selection_popup.verify_no_stores_found_message()
        self.assertTrue(no_stores_message, "No stores found message not displayed for 'Antarctica'.")

    def test_TC_2015_verify_boston_store_address_in_results(self):
        # 1. Launch homepage.
        loaded = self.homepage.load()
        self.assertTrue(loaded, "Foot Locker homepage did not load successfully.")
        # 2. Click 'Find a Store', then 'Select My Store' to open Store Locator popup.
        clicked = self.header.click_find_a_store()
        self.assertTrue(clicked, "Failed to click 'Find a Store' link.")
        select_store_clicked = self.find_store_popup.click_select_my_store()
        self.assertTrue(select_store_clicked, "Failed to click 'Select My Store' link.")
        store_popup_visible = self.store_selection_popup.is_popup_window_visible()
        self.assertTrue(store_popup_visible, "Store Locator popup did not appear.")
        # 3. Enter 'Boston, MA' in 'Location' textbox.
        location_entered = self.store_selection_popup.enter_location('Boston, MA')
        self.assertTrue(location_entered, "Failed to enter 'Boston, MA' in the Location textbox.")
        # 4. Click 'Search for Stores'.
        search_clicked = self.store_selection_popup.click_search()
        self.assertTrue(search_clicked, "Failed to click 'Search for Stores' button.")
        # 5. Verify store with address '375 Washington Street, Boston, MA 02108' is present in results and matches exactly.
        address_found = self.store_selection_popup.verify_store_address_in_results('375 Washington Street, Boston, MA 02108')
        self.assertTrue(address_found, "Store with address '375 Washington Street, Boston, MA 02108' not found in results or does not match exactly.")

    def test_TC_2016_set_and_verify_preferred_store(self):
        # 1. Launch homepage.
        loaded = self.homepage.load()
        self.assertTrue(loaded, "Foot Locker homepage did not load successfully.")
        # 2. Click 'Find a Store', then 'Select My Store' to open Store Locator popup.
        clicked = self.header.click_find_a_store()
        self.assertTrue(clicked, "Failed to click 'Find a Store' link.")
        select_store_clicked = self.find_store_popup.click_select_my_store()
        self.assertTrue(select_store_clicked, "Failed to click 'Select My Store' link.")
        store_popup_visible = self.store_selection_popup.is_popup_window_visible()
        self.assertTrue(store_popup_visible, "Store Locator popup did not appear.")
        # 3. Enter 'Boston, MA' in 'Location' textbox.
        location_entered = self.store_selection_popup.enter_location('Boston, MA')
        self.assertTrue(location_entered, "Failed to enter 'Boston, MA' in the Location textbox.")
        # 4. Click 'Search for Stores'.
        search_clicked = self.store_selection_popup.click_search()
        self.assertTrue(search_clicked, "Failed to click 'Search for Stores' button.")
        # 5. Click 'Set My Store' for store at '375 Washington Street, Boston, MA 02108'.
        set_store_clicked = self.store_selection_popup.set_my_store_by_address('375 Washington Street, Boston, MA 02108')
        self.assertTrue(set_store_clicked, "Failed to set '375 Washington Street, Boston, MA 02108' as preferred store.")
        # 6. Verify selected store is saved as preferred store.
        preferred_store_verified = self.store_selection_popup.verify_preferred_store('375 Washington Street, Boston, MA 02108')
        self.assertTrue(preferred_store_verified, "Preferred store is not saved as '375 Washington Street, Boston, MA 02108'.")

if __name__ == '__main__':
    unittest.main()
