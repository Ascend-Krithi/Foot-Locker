import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup

class TestFootLocker(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.driver.maximize_window()
        cls.home_page = HomePage(cls.driver)
        cls.store_locator_popup = StoreLocatorPopup(cls.driver)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    # Existing test methods...

    def test_2107_store_locator_massachusetts(self):
        ...
    def test_2108_store_locator_invalid_city(self):
        ...

    def test_2109_use_my_location(self):
        """
        TestCase 2109:
        - Step 1: Launch the Foot Locker website and navigate to the Store Locator page.
        - Step 2: Ensure browser location permission is enabled.
        - Step 3: Click on the 'Use My Location' button.
        Expected: Store Locator page displays, browser prompts for location, and a list of stores near the user's current location is displayed.
        """
        self.home_page.go_to_homepage()
        self.home_page.go_to_store_locator()
        # Step 2: Ensure browser location permission is enabled (handled by browser, may require manual intervention in real runs)
        self.store_locator_popup.click_use_my_location()
        # Wait for location prompt and list to load
        try:
            WebDriverWait(self.driver, 10).until(
                EC.presence_of_element_located((By.CSS_SELECTOR, ".store-list-item"))
            )
            store_list = self.store_locator_popup.get_store_list()
            self.assertTrue(len(store_list) > 0, "Store list should not be empty after using 'Use My Location'.")
        except TimeoutException:
            self.fail("Store list did not load after using 'Use My Location'.")

    def test_2110_store_locator_boston(self):
        """
        TestCase 2110:
        - Step 1: Launch the Foot Locker website and navigate to the Store Locator page.
        - Step 2: Perform a search for 'Boston, MA'.
        - Step 3: Click on the store with address '375 Washington Street, Boston, MA 02108'.
        Expected: Store Locator page displays, list of stores in Boston is shown, and store details/popup displays correct address, hours, and contact info.
        """
        self.home_page.go_to_homepage()
        self.home_page.go_to_store_locator()
        self.store_locator_popup.search_location("Boston, MA")
        # Wait for results
        try:
            WebDriverWait(self.driver, 10).until(
                EC.text_to_be_present_in_element((By.CSS_SELECTOR, ".store-list-item"), "Boston")
            )
        except TimeoutException:
            self.fail("Store list did not update for 'Boston, MA'.")
        # Click the specific store
        store_found = self.store_locator_popup.click_store_by_address("375 Washington Street, Boston, MA 02108")
        self.assertTrue(store_found, "Could not find the store with address '375 Washington Street, Boston, MA 02108'.")
        # Validate store details
        details = self.store_locator_popup.get_store_details()
        self.assertIn("375 Washington Street, Boston, MA 02108", details.get('address', ''))
        self.assertTrue(details.get('hours'), "Store hours should be displayed.")
        self.assertTrue(details.get('contact'), "Contact info should be displayed.")
