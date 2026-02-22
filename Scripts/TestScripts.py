import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import time

from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup
from Pages.StoreSelectionPopup import StoreSelectionPopup

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

    def test_2073_homepage_store_popup_pom(self):
        """
        TestCase 2073:
        1. Launch the Foot Locker website in a browser.
        2. Locate and click on the 'Find a Store' option on the homepage.
        3. Observe the popup message.
        4. Verify the presence of the 'Select My Store' link within the popup.
        """
        driver = self.driver
        homepage = HomePage(driver)
        homepage.load_homepage()
        # Step 1: Homepage loaded (implicitly checked by waiting for Find a Store)
        # Step 2: Click 'Find a Store'
        homepage.click_find_a_store()
        popup = StoreLocatorPopup(driver)
        # Step 3: Verify popup is visible
        self.assertTrue(popup.is_popup_visible())
        # Step 4: Check popup message
        message = popup.get_popup_message()
        self.assertEqual(message.strip(), 'Choose a preferred store to make shopping easier')
        # Step 5: Verify 'Select My Store' link is visible
        self.assertTrue(popup.is_select_my_store_link_visible())

    def test_2074_store_selection_popup_pom(self):
        """
        TestCase 2074:
        1. Launch the Foot Locker website in a browser.
        2. Click on 'Find a Store' to open the popup.
        3. Click on the 'Select My Store' link within the popup.
        4. Verify the presence of the 'Location' textbox and 'Search for Stores' button in the popup.
        """
        driver = self.driver
        homepage = HomePage(driver)
        homepage.load_homepage()
        homepage.click_find_a_store()
        popup = StoreLocatorPopup(driver)
        self.assertTrue(popup.is_popup_visible())
        popup.click_select_my_store_link()
        selection_popup = StoreSelectionPopup(driver)
        # Step 4: Verify location textbox and search button
        self.assertTrue(selection_popup.is_location_textbox_present())
        self.assertTrue(selection_popup.is_search_button_present())

if __name__ == "__main__":
    unittest.main()
