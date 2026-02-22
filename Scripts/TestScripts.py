import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time

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

if __name__ == "__main__":
    unittest.main()
