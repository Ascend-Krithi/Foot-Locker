# Existing imports and code remain unchanged
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
        """
        Test Case 2077:
        1. Launch the Foot Locker website and navigate to the store locator popup.
        2. Enter 'Boston, MA' in the 'Location' textbox and click 'Search for Stores'.
        3. Locate the store with address '375 Washington Street, Boston, MA 02108' in the results.
        4. Click on 'Set My Store' for the Boston location.
        """
        driver = self.driver
        # ... (existing logic)

    def test_2078_confirm_boston_store_set_and_persisted(self):
        """
        Test Case 2078:
        1. Launch the Foot Locker website and set the preferred store to '375 Washington Street, Boston, MA 02108' using the store locator popup.
        2. Observe the confirmation indicator after setting the store.
        3. Verify that the selected store appears across the website.
        """
        driver = self.driver
        # ... (existing logic)

    # Appended test methods for new test cases
    def test_2081_homepage_find_store_popup(self):
        """
        Test Case 2081:
        1. Launch the Foot Locker website homepage.
        2. Click on the 'Find a Store' link in the header.
        3. Click on the 'Select My Store' link in the popup.
        """
        driver = self.driver
        homepage = HomePage(driver)
        homepage.load_homepage()
        # Verify homepage loads successfully
        self.assertTrue(driver.current_url.startswith('https://www.footlocker.com/'))
        homepage.click_find_a_store()
        # Verify popup appears
        store_locator_popup = StoreLocatorPopup(driver)
        self.assertTrue(store_locator_popup.is_popup_visible())
        # Click 'Select My Store'
        store_locator_popup.click_select_my_store_button()
        # Verify 'Location' textbox and 'Search for Stores' button are visible
        store_selection_popup = StoreSelectionPopup(driver)
        self.assertTrue(store_selection_popup.is_location_textbox_present())
        self.assertTrue(store_selection_popup.is_search_button_present())

    def test_2082_search_store_boston(self):
        """
        Test Case 2082:
        1. Launch the Foot Locker website homepage.
        2. Click on the 'Find a Store' link.
        3. Click on the 'Select My Store' link in the popup.
        4. Enter 'Boston, MA' in the 'Location' textbox.
        5. Click the 'Search for Stores' button.
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
        # Verify Boston, MA is entered
        textbox = driver.find_element_by_id('store-locator-search-input')
        self.assertEqual(textbox.get_attribute('value'), 'Boston, MA')
        self.assertTrue(store_selection_popup.is_search_button_present())
        store_selection_popup.click_search_for_stores()
        # Verify search results (basic check for store items appearing)
        store_items = driver.find_elements_by_xpath("//div[@class='store-item']")
        self.assertGreater(len(store_items), 0)

if __name__ == '__main__':
    unittest.main()
