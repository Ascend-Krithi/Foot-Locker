import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
import time

class TestFootLockerStoreLocator(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.maximize_window()

    def tearDown(self):
        self.driver.quit()

    def test_find_store_boston(self):
        driver = self.driver
        driver.get('https://www.footlocker.com')
        time.sleep(2)
        # Step 2: Click 'Find a Store'
        driver.find_element(By.LINK_TEXT, 'Find a Store').click()
        time.sleep(1)
        # Step 2: Click 'Select My Store'
        driver.find_element(By.LINK_TEXT, 'Select My Store').click()
        time.sleep(1)
        # Step 3: Enter 'Boston, MA' in location textbox
        location_box = driver.find_element(By.ID, 'locationTextbox')  # Placeholder ID
        location_box.clear()
        location_box.send_keys('Boston, MA')
        time.sleep(1)
        # Step 4: Click 'Search for Stores'
        driver.find_element(By.ID, 'searchStoresButton').click()  # Placeholder ID
        time.sleep(2)
        # Step 5: Validate results
        results = driver.find_elements(By.CLASS_NAME, 'store-result')  # Placeholder class
        self.assertTrue(len(results) > 0, "No stores found for Boston, MA")

    def test_find_store_antarctica(self):
        driver = self.driver
        driver.get('https://www.footlocker.com')
        time.sleep(2)
        # Step 2: Click 'Find a Store'
        driver.find_element(By.LINK_TEXT, 'Find a Store').click()
        time.sleep(1)
        # Step 2: Click 'Select My Store'
        driver.find_element(By.LINK_TEXT, 'Select My Store').click()
        time.sleep(1)
        # Step 3: Enter 'Antarctica' in location textbox
        location_box = driver.find_element(By.ID, 'locationTextbox')  # Placeholder ID
        location_box.clear()
        location_box.send_keys('Antarctica')
        time.sleep(1)
        # Step 4: Click 'Search for Stores'
        driver.find_element(By.ID, 'searchStoresButton').click()  # Placeholder ID
        time.sleep(2)
        # Step 5: Validate 'no stores found' message
        no_stores_msg = driver.find_element(By.ID, 'noStoresMessage')  # Placeholder ID
        self.assertTrue(no_stores_msg.is_displayed(), "No stores message not displayed for Antarctica")

    def test_set_preferred_store_and_confirm(self):
        driver = self.driver
        driver.get('https://www.footlocker.com')
        time.sleep(2)
        # Step 2: Click 'Find a Store'
        driver.find_element(By.LINK_TEXT, 'Find a Store').click()
        time.sleep(1)
        # Step 2: Click 'Select My Store'
        driver.find_element(By.LINK_TEXT, 'Select My Store').click()
        time.sleep(1)
        # Step 3: Enter 'Boston, MA' in location textbox
        location_box = driver.find_element(By.ID, 'locationTextbox')  # Placeholder ID
        location_box.clear()
        location_box.send_keys('Boston, MA')
        time.sleep(1)
        # Step 4: Click 'Search for Stores'
        driver.find_element(By.ID, 'searchStoresButton').click()  # Placeholder ID
        time.sleep(2)
        # Step 5: Click 'Set My Store' for 375 Washington Street
        stores = driver.find_elements(By.CLASS_NAME, 'store-result')  # Placeholder class
        found = False
        for store in stores:
            if '375 Washington Street' in store.text:
                set_button = store.find_element(By.XPATH, ".//button[contains(text(), 'Set My Store')]")
                set_button.click()
                found = True
                break
        self.assertTrue(found, "Store at 375 Washington Street not found")
        time.sleep(2)
        # Step 6: Verify confirmation indicator
        try:
            confirmation = driver.find_element(By.ID, 'confirmationIndicator')  # Placeholder ID
            self.assertTrue(confirmation.is_displayed(), "Confirmation indicator not displayed")
        except:
            self.fail("Confirmation indicator not found after setting preferred store")
        # Verify store name appears consistently
        header_store = driver.find_element(By.ID, 'preferredStoreHeader')  # Placeholder ID
        self.assertIn('375 Washington Street', header_store.text, "Preferred store not shown in header")

    def test_preferred_store_persists_across_pages(self):
        driver = self.driver
        driver.get('https://www.footlocker.com')
        time.sleep(2)
        # Step 2: Click 'Find a Store'
        driver.find_element(By.LINK_TEXT, 'Find a Store').click()
        time.sleep(1)
        # Step 2: Click 'Select My Store'
        driver.find_element(By.LINK_TEXT, 'Select My Store').click()
        time.sleep(1)
        # Step 3: Enter 'Boston, MA' in location textbox
        location_box = driver.find_element(By.ID, 'locationTextbox')  # Placeholder ID
        location_box.clear()
        location_box.send_keys('Boston, MA')
        time.sleep(1)
        # Step 4: Click 'Search for Stores'
        driver.find_element(By.ID, 'searchStoresButton').click()  # Placeholder ID
        time.sleep(2)
        # Step 5: Click 'Set My Store' for 375 Washington Street
        stores = driver.find_elements(By.CLASS_NAME, 'store-result')  # Placeholder class
        found = False
        for store in stores:
            if '375 Washington Street' in store.text:
                set_button = store.find_element(By.XPATH, ".//button[contains(text(), 'Set My Store')]")
                set_button.click()
                found = True
                break
        self.assertTrue(found, "Store at 375 Washington Street not found")
        time.sleep(2)
        # Step 6: Navigate to Men's Sneakers page
        driver.get('https://www.footlocker.com/men/shoes')
        time.sleep(2)
        # Verify preferred store persists and is visible
        header_store = driver.find_element(By.ID, 'preferredStoreHeader')  # Placeholder ID
        self.assertIn('375 Washington Street', header_store.text, "Preferred store not shown in header on Men's Sneakers page")

if __name__ == "__main__":
    unittest.main()
