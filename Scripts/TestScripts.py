import unittest
from selenium import webdriver
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup

class TestStoreLocator(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)
        self.driver.get('https://www.footlocker.com/store-locator')
        self.home_page = HomePage(self.driver)
        self.store_locator_popup = StoreLocatorPopup(self.driver)

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

# Note: Ensure StoreLocatorPopup has a get_list_of_stores() method that returns a list of store elements.
