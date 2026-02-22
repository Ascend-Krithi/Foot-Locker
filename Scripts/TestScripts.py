import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import time

class TestFootLocker(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        options = Options()
        options.add_argument('--headless')
        cls.driver = webdriver.Chrome(options=options)
        cls.driver.implicitly_wait(10)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    def test_2011_launch_homepage_and_find_store_popup(self):
        """
        TestCase 2011:
        1. Launch the Foot Locker website homepage in a browser.
        2. Click on the 'Find a Store' link in the website header.
        """
        driver = self.driver
        driver.get('https://www.footlocker.com')
        # Step 1: Verify homepage loads
        self.assertIn('Foot Locker', driver.title)
        # Step 2: Click 'Find a Store' link
        find_store = driver.find_element(By.LINK_TEXT, 'Find a Store')
        find_store.click()
        time.sleep(2)
        # Verify popup appears with message and 'Select My Store' link
        popup = driver.find_element(By.XPATH, "//div[contains(text(), 'Choose a preferred store to make shopping easier')]")
        self.assertTrue(popup.is_displayed())
        select_my_store = driver.find_element(By.LINK_TEXT, 'Select My Store')
        self.assertTrue(select_my_store.is_displayed())

    def test_2012_find_store_select_my_store(self):
        """
        TestCase 2012:
        1. Launch the Foot Locker website homepage in a browser.
        2. Click on the 'Find a Store' link in the website header.
        3. Click on the 'Select My Store' link within the popup.
        """
        driver = self.driver
        driver.get('https://www.footlocker.com')
        # Step 1: Verify homepage loads
        self.assertIn('Foot Locker', driver.title)
        # Step 2: Click 'Find a Store' link
        find_store = driver.find_element(By.LINK_TEXT, 'Find a Store')
        find_store.click()
        time.sleep(2)
        # Step 3: Click 'Select My Store' link
        select_my_store = driver.find_element(By.LINK_TEXT, 'Select My Store')
        select_my_store.click()
        time.sleep(2)
        # Verify location textbox and search button appear
        location_textbox = driver.find_element(By.XPATH, "//input[@placeholder='Location']")
        search_button = driver.find_element(By.XPATH, "//button[contains(text(), 'Search for Stores')]")
        self.assertTrue(location_textbox.is_displayed())
        self.assertTrue(search_button.is_displayed())

if __name__ == "__main__":
    unittest.main()
