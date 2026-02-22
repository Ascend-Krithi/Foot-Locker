import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
from Pages.HomePage import HomePage
from Pages.StoreLocatorPage import StoreLocatorPage

class TestStoreLocator(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)

    def tearDown(self):
        self.driver.quit()

    def test_search_boston_store(self):
        driver = self.driver
        driver.get('https://www.footlocker.com')
        # Click on 'Find a Store'
        find_store = driver.find_element(By.LINK_TEXT, 'Find a Store')
        find_store.click()
        # Click on 'Select My Store'
        select_my_store = driver.find_element(By.LINK_TEXT, 'Select My Store')
        select_my_store.click()
        # Enter 'Boston, MA' in 'Location' textbox
        location_box = driver.find_element(By.ID, 'store-search-location-input')
        location_box.clear()
        location_box.send_keys('Boston, MA')
        # Click on 'Search for Stores'
        search_button = driver.find_element(By.ID, 'store-search-submit-btn')
        search_button.click()
        # Wait for results
        time.sleep(3)
        # Validate results
        results = driver.find_elements(By.CSS_SELECTOR, '.StoreList-results .StoreListStore')
        self.assertTrue(len(results) > 0, "Expected to find stores in or near Boston.")

    def test_search_antarctica_store(self):
        driver = self.driver
        driver.get('https://www.footlocker.com')
        # Click on 'Find a Store'
        find_store = driver.find_element(By.LINK_TEXT, 'Find a Store')
        find_store.click()
        # Click on 'Select My Store'
        select_my_store = driver.find_element(By.LINK_TEXT, 'Select My Store')
        select_my_store.click()
        # Enter 'Antarctica' in 'Location' textbox
        location_box = driver.find_element(By.ID, 'store-search-location-input')
        location_box.clear()
        location_box.send_keys('Antarctica')
        # Click on 'Search for Stores'
        search_button = driver.find_element(By.ID, 'store-search-submit-btn')
        search_button.click()
        # Wait for results
        time.sleep(3)
        # Validate 'no stores found' message
        no_stores_msg = driver.find_element(By.CSS_SELECTOR, '.StoreList-noResults')
        self.assertIn('no stores', no_stores_msg.text.lower())

    def test_store_locator_2015_validate_store_present(self):
        """
        Test Case 2015:
        Validate that the store with address '375 Washington Street, Boston, MA 02108' is present in results after searching for 'Boston, MA'.
        """
        driver = self.driver
        homepage = HomePage(driver)
        store_locator = StoreLocatorPage(driver)
        homepage.load_homepage('https://www.footlocker.com')
        homepage.click_find_a_store()
        homepage.click_select_my_store()
        store_locator.enter_location('Boston, MA')
        store_locator.click_search_for_stores()
        time.sleep(3)
        address = '375 Washington Street, Boston, MA 02108'
        self.assertTrue(store_locator.is_store_in_results(address), f"Store with address '{address}' not found in results.")

    def test_store_locator_2016_set_my_store(self):
        """
        Test Case 2016:
        Click 'Set My Store' for the store at '375 Washington Street, Boston, MA 02108' after searching for 'Boston, MA'.
        """
        driver = self.driver
        homepage = HomePage(driver)
        store_locator = StoreLocatorPage(driver)
        homepage.load_homepage('https://www.footlocker.com')
        homepage.click_find_a_store()
        homepage.click_select_my_store()
        store_locator.enter_location('Boston, MA')
        store_locator.click_search_for_stores()
        time.sleep(3)
        address = '375 Washington Street, Boston, MA 02108'
        self.assertTrue(store_locator.click_set_my_store(address), f"Could not click 'Set My Store' for store at '{address}'.")

if __name__ == "__main__":
    unittest.main()
