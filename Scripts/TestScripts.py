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
        """
        Test Case 2085:
        1. Launch homepage.
        2. Click 'Find a Store'.
        3. Click 'Select My Store'.
        4. Enter 'Boston, MA'.
        5. Click 'Search for Stores'.
        6. Locate store with address '375 Washington Street, Boston, MA 02108'.
        7. Verify address format.
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
        store_card = driver.find_element_by_xpath("//div[contains(text(), '375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class, 'store-card')]")
        self.assertIsNotNone(store_card)
        address_text = driver.find_element_by_xpath("//div[contains(text(), '375 Washington Street, Boston, MA 02108')]").text
        self.assertEqual(address_text, '375 Washington Street, Boston, MA 02108')

    def test_2086_set_my_store_and_verify(self):
        """
        Test Case 2086:
        1. Launch homepage.
        2. Click 'Find a Store'.
        3. Click 'Select My Store'.
        4. Enter 'Boston, MA'.
        5. Click 'Search for Stores'.
        6. Locate store with address '375 Washington Street, Boston, MA 02108'.
        7. Click 'Set My Store'.
        8. Verify preferred store is saved.
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

    def test_2087_store_selection_workflow(self):
        """
        Test Case 2087:
        1. Launch homepage.
        2. Click 'Find a Store'.
        3. Click 'Select My Store'.
        4. Enter 'Boston, MA' in the location textbox.
        5. Click 'Search for Stores'.
        6. Click 'Set My Store' for a store other than '375 Washington Street, Boston, MA 02108'.
        7. Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'.
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
        # Step 6: Set My Store for a store other than '375 Washington Street, Boston, MA 02108'
        other_store_btns = driver.find_elements_by_xpath("//div[@class='store-item'][not(.//div[contains(text(), '375 Washington Street, Boston, MA 02108')])]//button[contains(text(), 'Set My Store')]")
        if other_store_btns:
            other_store_btns[0].click()
        else:
            self.fail("No alternative store button found.")
        # Step 7: Set My Store for '375 Washington Street, Boston, MA 02108'
        set_my_store_btn = driver.find_element_by_xpath("//div[contains(text(), '375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class, 'store-card')]//button[contains(text(), 'Set My Store')]")
        set_my_store_btn.click()
        confirmation_page = ConfirmationPage(driver)
        self.assertTrue(confirmation_page.is_confirmation_visible())
        self.assertTrue(confirmation_page.is_my_store_indicator_visible())

    def test_2088_store_selection_confirmation(self):
        """
        Test Case 2088:
        1. Launch homepage.
        2. Click 'Find a Store'.
        3. Click 'Select My Store'.
        4. Enter 'Boston, MA'.
        5. Click 'Search for Stores'.
        6. Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'.
        7. After clicking, verify that a confirmation indicator is displayed.
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
        set_my_store_btn = driver.find_element_by_xpath("//div[contains(text(), '375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class, 'store-card')]//button[contains(text(), 'Set My Store')]")
        set_my_store_btn.click()
        confirmation_page = ConfirmationPage(driver)
        self.assertTrue(confirmation_page.is_confirmation_visible())

if __name__ == '__main__':
    unittest.main()
