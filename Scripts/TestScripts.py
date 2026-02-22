# Selenium Python test scripts for Foot Locker store locator
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time

class TestFootLockerStoreLocator:
    def setup_method(self):
        self.driver = webdriver.Chrome()
        self.driver.maximize_window()

    def teardown_method(self):
        self.driver.quit()

    def test_set_preferred_store_confirmation(self):
        # Test Case 2017: SCRUM-15408 TS-005 TC-001
        driver = self.driver
        driver.get('https://www.footlocker.com')
        time.sleep(2)
        # Click 'Find a Store' and 'Select My Store'
        find_store = driver.find_element(By.LINK_TEXT, 'Find a Store')
        find_store.click()
        time.sleep(1)
        select_my_store = driver.find_element(By.LINK_TEXT, 'Select My Store')
        select_my_store.click()
        time.sleep(2)
        # Enter 'Boston, MA' in 'Location' textbox
        location_box = driver.find_element(By.ID, 'location')
        location_box.clear()
        location_box.send_keys('Boston, MA')
        # Click 'Search for Stores'
        search_button = driver.find_element(By.XPATH, "//button[contains(text(),'Search for Stores')]")
        search_button.click()
        time.sleep(2)
        # Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'
        set_my_store = driver.find_element(By.XPATH, "//div[contains(text(),'375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class,'store-info')]//button[contains(text(),'Set My Store')]")
        set_my_store.click()
        time.sleep(2)
        # Verify confirmation indicator
        confirmation = driver.find_element(By.XPATH, "//div[contains(@class,'confirmation-indicator') or contains(text(),'store set')]")
        assert confirmation.is_displayed(), 'Confirmation indicator not displayed.'

    def test_preferred_store_persistence(self):
        # Test Case 2018: SCRUM-15408 TS-006 TC-001
        driver = self.driver
        driver.get('https://www.footlocker.com')
        time.sleep(2)
        # Click 'Find a Store' and 'Select My Store'
        find_store = driver.find_element(By.LINK_TEXT, 'Find a Store')
        find_store.click()
        time.sleep(1)
        select_my_store = driver.find_element(By.LINK_TEXT, 'Select My Store')
        select_my_store.click()
        time.sleep(2)
        # Enter 'Boston, MA' in 'Location' textbox
        location_box = driver.find_element(By.ID, 'location')
        location_box.clear()
        location_box.send_keys('Boston, MA')
        # Click 'Search for Stores'
        search_button = driver.find_element(By.XPATH, "//button[contains(text(),'Search for Stores')]")
        search_button.click()
        time.sleep(2)
        # Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'
        set_my_store = driver.find_element(By.XPATH, "//div[contains(text(),'375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class,'store-info')]//button[contains(text(),'Set My Store')]")
        set_my_store.click()
        time.sleep(2)
        # Navigate to Men's Sneakers
        driver.get('https://www.footlocker.com/men/shoes')
        time.sleep(2)
        # Verify preferred store persists
        preferred_store = driver.find_element(By.XPATH, "//div[contains(@class,'preferred-store') and contains(text(),'375 Washington Street, Boston, MA 02108')]")
        assert preferred_store.is_displayed(), 'Preferred store not visible after navigation.'
