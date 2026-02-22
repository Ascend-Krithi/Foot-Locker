import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import time

# Assuming PageClasses are available in Scripts.PageClasses
from Scripts.PageClasses import Homepage, StoreLocatorPopup, StoreSelection, Confirmation, Navigation

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
        driver = self.driver
        driver.get('https://www.footlocker.com')
        self.assertIn('Foot Locker', driver.title)
        find_store = driver.find_element(By.LINK_TEXT, 'Find a Store')
        find_store.click()
        time.sleep(2)
        popup = driver.find_element(By.XPATH, "//div[contains(text(), 'Choose a preferred store to make shopping easier')]")
        self.assertTrue(popup.is_displayed())
        select_my_store = driver.find_element(By.LINK_TEXT, 'Select My Store')
        self.assertTrue(select_my_store.is_displayed())

    def test_2012_find_store_select_my_store(self):
        driver = self.driver
        driver.get('https://www.footlocker.com')
        self.assertIn('Foot Locker', driver.title)
        find_store = driver.find_element(By.LINK_TEXT, 'Find a Store')
        find_store.click()
        time.sleep(2)
        select_my_store = driver.find_element(By.LINK_TEXT, 'Select My Store')
        select_my_store.click()
        time.sleep(2)
        location_textbox = driver.find_element(By.XPATH, "//input[@placeholder='Location']")
        search_button = driver.find_element(By.XPATH, "//button[contains(text(), 'Search for Stores')]")
        self.assertTrue(location_textbox.is_displayed())
        self.assertTrue(search_button.is_displayed())

    def test_2017_store_selection_and_confirmation(self):
        driver = self.driver
        homepage = Homepage(driver)
        homepage.load()
        homepage.click_find_store()
        popup = StoreLocatorPopup(driver)
        popup.click_select_my_store()
        store_selection = StoreSelection(driver)
        store_selection.enter_location('Boston, MA')
        store_selection.click_search_for_stores()
        store_selection.set_my_store('375 Washington Street, Boston, MA 02108')
        confirmation = Confirmation(driver)
        self.assertTrue(confirmation.is_confirmation_displayed(), 'Confirmation indicator should be displayed after setting store.')

    def test_2018_store_selection_persistence(self):
        driver = self.driver
        homepage = Homepage(driver)
        homepage.load()
        homepage.click_find_store()
        popup = StoreLocatorPopup(driver)
        popup.click_select_my_store()
        store_selection = StoreSelection(driver)
        store_selection.enter_location('Boston, MA')
        store_selection.click_search_for_stores()
        store_selection.set_my_store('375 Washington Street, Boston, MA 02108')
        navigation = Navigation(driver)
        navigation.go_to_mens_sneakers()
        confirmation = Confirmation(driver)
        self.assertTrue(confirmation.is_confirmation_displayed(), 'Store selection indicator should persist after navigation.')

    # Test Case 2075: Launch homepage, click Find a Store and Select My Store, enter Boston, MA, click Search for Stores
    def test_2075_find_store_search_boston(self):
        driver = self.driver
        homepage = Homepage(driver)
        homepage.load_homepage('https://www.footlocker.com/')
        self.assertTrue(homepage.is_homepage_loaded(), 'Homepage should be loaded.')
        homepage.click_find_a_store()
        popup = StoreLocatorPopup(driver)
        popup.open_store_locator_popup()
        self.assertTrue(popup.is_store_locator_popup_open(), 'Store locator popup should be open.')
        popup.enter_location('Boston, MA')
        popup.click_search_for_stores()
        # Optionally add assertions for search results

    # Test Case 2076: Launch homepage, open store locator popup, enter Boston, MA, search, review for address, verify exact match
    def test_2076_verify_store_address(self):
        driver = self.driver
        homepage = Homepage(driver)
        homepage.load_homepage('https://www.footlocker.com/')
        homepage.click_find_a_store()
        popup = StoreLocatorPopup(driver)
        popup.open_store_locator_popup()
        popup.enter_location('Boston, MA')
        popup.click_search_for_stores()
        store_selection = StoreSelectionPopup(driver)
        self.assertTrue(store_selection.is_store_address_visible(), 'Store address should be visible in results.')
        self.assertTrue(store_selection.verify_store_address('375 Washington Street, Boston, MA 02108'), 'Store address should match exactly.')
