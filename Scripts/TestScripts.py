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
        # Step 1: Launch homepage
        loaded = self.homepage.load()
        self.assertTrue(loaded, "Foot Locker homepage did not load successfully.")
        # Step 2: Click 'Find a Store' link
        clicked = self.header.click_find_a_store()
        self.assertTrue(clicked, "Failed to click 'Find a Store' link.")
        # Verify popup appears
        popup_visible = self.find_store_popup.is_popup_visible()
        self.assertTrue(popup_visible, "Find a Store popup did not appear.")
        # Verify popup message
        message_ok = self.find_store_popup.verify_message()
        self.assertTrue(message_ok, "Popup message not as expected.")

    def test_TC_2012_select_my_store_popup(self):
        # Step 1: Launch homepage
        loaded = self.homepage.load()
        self.assertTrue(loaded, "Foot Locker homepage did not load successfully.")
        # Step 2: Click 'Find a Store' link
        clicked = self.header.click_find_a_store()
        self.assertTrue(clicked, "Failed to click 'Find a Store' link.")
        # Verify popup appears
        popup_visible = self.find_store_popup.is_popup_visible()
        self.assertTrue(popup_visible, "Find a Store popup did not appear.")
        # Click 'Select My Store' link
        select_store_clicked = self.find_store_popup.click_select_my_store()
        self.assertTrue(select_store_clicked, "Failed to click 'Select My Store' link.")
        # Verify Store Selection popup window
        store_popup_visible = self.store_selection_popup.is_popup_window_visible()
        self.assertTrue(store_popup_visible, "Store Selection popup window did not appear.")
        # Optionally, verify location textbox and search button are present (not in test steps, but can be added)

if __name__ == '__main__':
    unittest.main()
