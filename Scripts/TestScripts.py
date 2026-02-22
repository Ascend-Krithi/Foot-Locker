import unittest
from selenium import webdriver
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup
from Pages.StoreSelectionPopup import StoreSelectionPopup
from Pages.ConfirmationPage import ConfirmationPage

class FootLockerTests(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)

    def tearDown(self):
        self.driver.quit()

    # ... (existing test methods remain unchanged)

    def test_2091_store_selection_and_persistence(self):
        """
        Test Case 2091: Store selection workflow and preferred store persistence.
        Steps:
        1. Launch homepage.
        2. Click 'Find a Store'.
        3. Click 'Select My Store'.
        4. Enter 'Boston, MA' in the location textbox.
        5. Click 'Search for Stores'.
        6. Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'.
        7. Refresh page and verify preferred store persists.
        """
        driver = self.driver
        homepage = HomePage(driver)
        homepage.load_homepage()
        self.assertTrue(driver.current_url.startswith('https://www.footlocker.com/'))
        homepage.click_find_a_store()
        store_locator_popup = StoreLocatorPopup(driver)
        self.assertTrue(store_locator_popup.is_popup_visible())
        store_locator_popup.click_select_my_store_button()
        store_selection_popup = StoreSelectionPopup(driver)
        self.assertTrue(store_selection_popup.is_location_textbox_present())
        store_selection_popup.enter_location('Boston, MA')
        textbox = driver.find_element_by_id('store-locator-search-input')
        self.assertEqual(textbox.get_attribute('value'), 'Boston, MA')
        self.assertTrue(store_selection_popup.is_search_button_present())
        store_selection_popup.click_search_for_stores()
        store_selection_popup.click_set_my_store()
        confirmation_page = ConfirmationPage(driver)
        self.assertTrue(confirmation_page.is_confirmation_visible())
        self.assertTrue(confirmation_page.is_my_store_indicator_visible())
        # Refresh page and check persistence
        driver.refresh()
        homepage = HomePage(driver)
        self.assertTrue(homepage.is_my_store_indicator_visible())

    def test_2092_store_locator_popup_ui(self):
        """
        Test Case 2092: Store locator popup and UI validation.
        Steps:
        1. Launch homepage.
        2. Click 'Find a Store'.
        3. Click 'Select My Store' and verify popup UI elements.
        """
        driver = self.driver
        homepage = HomePage(driver)
        homepage.load_homepage()
        self.assertTrue(driver.current_url.startswith('https://www.footlocker.com/'))
        homepage.click_find_a_store()
        store_locator_popup = StoreLocatorPopup(driver)
        self.assertTrue(store_locator_popup.is_popup_visible())
        popup_message = store_locator_popup.get_popup_message()
        self.assertIn('Choose a preferred store to make shopping easier', popup_message)
        self.assertTrue(store_locator_popup.is_select_my_store_button_visible())
        store_locator_popup.click_select_my_store_button()
        store_selection_popup = StoreSelectionPopup(driver)
        self.assertTrue(store_selection_popup.is_location_textbox_present())
        self.assertTrue(store_selection_popup.is_search_button_present())

if __name__ == '__main__':
    unittest.main()
