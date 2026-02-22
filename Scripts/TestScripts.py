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

    def test_2077_set_boston_store_via_store_locator(self):
        pass

    def test_2078_confirm_boston_store_set_and_persisted(self):
        pass

    def test_2081_homepage_find_store_popup(self):
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
        self.assertTrue(store_selection_popup.is_search_button_present())

    def test_2082_search_store_boston(self):
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
        store_items = driver.find_elements_by_xpath("//div[@class='store-item']")
        self.assertGreater(len(store_items), 0)

    def test_2085_validate_store_address(self):
        # ... (truncated for brevity)
        pass

    def test_2080_homepage_find_store_popup_message(self):
        """
        Test Case 2080:
        1. Launch the Foot Locker website homepage (https://www.footlocker.com/) and verify that the homepage loads successfully.
        2. Locate and click on the 'Find a Store' link in the header and verify that a popup appears below 'Find a Store'.
        3. Observe the popup and verify it displays the message 'Choose a preferred store to make shopping easier' and a visible 'Select My Store' link.
        """
        driver = self.driver
        homepage = HomePage(driver)
        driver.get('https://www.footlocker.com/')
        self.assertTrue(homepage.is_homepage_loaded(), "Homepage did not load successfully.")
        homepage.click_find_a_store()
        store_locator_popup = StoreLocatorPopup(driver)
        self.assertTrue(store_locator_popup.is_popup_visible(), "Store Locator popup did not appear.")
        self.assertTrue(store_locator_popup.is_popup_message_visible(), "Popup message is not visible.")
        popup_message = store_locator_popup.get_popup_message()
        self.assertEqual(popup_message.strip(), "Choose a preferred store to make shopping easier", "Popup message text did not match expected.")
        self.assertTrue(driver.find_element(*StoreLocatorPopup.SELECT_MY_STORE_BUTTON).is_displayed(), "Select My Store button is not visible.")

if __name__ == '__main__':
    unittest.main()
