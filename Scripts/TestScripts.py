# Existing imports and code...
import unittest
from selenium import webdriver
from Pages/HomePage import HomePage
from Pages/StoreLocatorPopup import StoreLocatorPopup

class TestScripts(unittest.TestCase):
    # ... existing test methods ...

    def test_2103_homepage_find_a_store_popup(self):
        """
        testCaseId 2103:
        1. Launch homepage
        2. Check 'Find a Store' link visible
        3. Click 'Find a Store'
        4. Popup appears
        """
        driver = webdriver.Chrome()  # or your configured driver
        try:
            home_page = HomePage(driver)
            self.assertTrue(home_page.is_homepage_loaded(), "Homepage did not load correctly.")
            self.assertTrue(home_page.is_find_a_store_visible(), "'Find a Store' link is not visible on the homepage.")
            home_page.click_find_a_store()
            popup = StoreLocatorPopup(driver)
            self.assertTrue(popup.is_popup_visible(), "Store locator popup did not appear after clicking 'Find a Store'.")
        finally:
            driver.quit()

    def test_2104_find_a_store_popup_message_and_link(self):
        """
        testCaseId 2104:
        1. Launch homepage
        2. Click 'Find a Store'
        3. Popup displays message and 'Select My Store' link
        """
        driver = webdriver.Chrome()  # or your configured driver
        try:
            home_page = HomePage(driver)
            self.assertTrue(home_page.is_homepage_loaded(), "Homepage did not load correctly.")
            home_page.click_find_a_store()
            popup = StoreLocatorPopup(driver)
            self.assertTrue(popup.is_popup_visible(), "Store locator popup did not appear after clicking 'Find a Store'.")
            expected_message = "Choose a preferred store to make shopping easier"
            self.assertTrue(popup.is_popup_message_visible(), "Popup message is not visible.")
            self.assertEqual(popup.get_popup_message(), expected_message, f"Expected popup message '{expected_message}', got '{popup.get_popup_message()}'")
            self.assertTrue(popup.is_popup_message_visible(), "Popup message is not visible.")
            self.assertTrue(popup.click_select_my_store is not None, "'Select My Store' link is not available on the popup.")
        finally:
            driver.quit()
