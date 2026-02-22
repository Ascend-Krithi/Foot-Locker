import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
import time

class TestFootLockerStoreLocator(unittest.TestCase):
    def setUp(self):
        chrome_options = Options()
        chrome_options.add_argument('--headless')
        self.driver = webdriver.Chrome(options=chrome_options)
        self.driver.implicitly_wait(10)

    def tearDown(self):
        self.driver.quit()

    def test_store_locator_search_and_set_preferred_store(self):
        driver = self.driver
        driver.get('https://www.footlocker.com/')
        # Assume store locator popup appears automatically, otherwise trigger it here
        # Click on Store Locator popup button if needed
        try:
            store_locator_button = driver.find_element(By.XPATH, "//button[contains(text(), 'Find a Store') or contains(text(), 'Store Locator')]")
            store_locator_button.click()
            time.sleep(2)
        except Exception:
            pass  # Popup may already be open
        # Enter 'Boston, MA' in the location textbox
        location_input = driver.find_element(By.XPATH, "//input[@placeholder='Enter location' or @aria-label='Location']")
        location_input.clear()
        location_input.send_keys('Boston, MA')
        # Click search
        search_button = driver.find_element(By.XPATH, "//button[contains(text(), 'Search') or contains(text(), 'Find Stores')]")
        search_button.click()
        time.sleep(3)
        # Locate the store with address '375 Washington Street, Boston, MA 02108'
        store_result = driver.find_element(By.XPATH, "//*[contains(text(),'375 Washington Street') and contains(text(),'Boston, MA 02108')]")
        self.assertIsNotNone(store_result)
        # Click 'Set My Store' for this location
        set_store_button = store_result.find_element(By.XPATH, ".//following::button[contains(text(), 'Set My Store')][1]")
        set_store_button.click()
        time.sleep(2)
        # Confirm preferred store is set (confirmation message or highlight)
        confirmation = driver.find_element(By.XPATH, "//*[contains(text(),'preferred store') or contains(text(),'set as your store') or contains(text(),'Saved')]")
        self.assertIsNotNone(confirmation)

    def test_preferred_store_persistence(self):
        driver = self.driver
        driver.get('https://www.footlocker.com/')
        # Check for preferred store indicator
        try:
            preferred_store = driver.find_element(By.XPATH, "//*[contains(text(),'375 Washington Street') and contains(text(),'Boston, MA 02108')]")
            self.assertTrue(preferred_store.is_displayed())
        except Exception:
            self.fail('Preferred store is not visible across the website.')

if __name__ == "__main__":
    unittest.main()
