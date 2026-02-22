# Scripts/TestScripts.py
import unittest
from selenium import webdriver
from PageClasses.HomePage import HomePage
from PageClasses.StoreLocatorPopup import StoreLocatorPopup
from PageClasses.StoreSelectionPopup import StoreSelectionPopup
from PageClasses.ConfirmationPage import ConfirmationPage
from PageClasses.ProductListingPage import ProductListingPage

class TestScripts(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.driver.implicitly_wait(10)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    # ... existing test methods ...

    def test_TC_2099_set_preferred_store_boston(self):
        """
        Test Case 2099: SCRUM-15408 TS-001 TC-008
        Steps:
        1. Load the homepage
        2. Click 'Find a Store'
        3. Select 'My Store'
        4. Search for 'Boston, MA'
        5. Set store at '375 Washington Street, Boston, MA 02108' as preferred
        """
        driver = self.driver
        home_page = HomePage(driver)
        store_locator = StoreLocatorPopup(driver)
        store_selection = StoreSelectionPopup(driver)
        confirmation_page = ConfirmationPage(driver)

        # Step 1: Load the homepage
        home_page.load()
        self.assertTrue(home_page.is_loaded(), "Home page did not load correctly.")

        # Step 2: Click 'Find a Store'
        home_page.click_find_a_store()
        self.assertTrue(store_locator.is_displayed(), "Store Locator popup did not appear.")

        # Step 3: Select 'My Store'
        store_locator.select_my_store_tab()
        self.assertTrue(store_locator.is_my_store_tab_active(), "'My Store' tab is not active.")

        # Step 4: Search for 'Boston, MA'
        store_locator.enter_search_text('Boston, MA')
        store_locator.click_search_button()
        self.assertTrue(store_selection.is_search_result_displayed('Boston, MA'), "Search results for 'Boston, MA' not displayed.")

        # Step 5: Set store at '375 Washington Street, Boston, MA 02108' as preferred
        store_address = '375 Washington Street, Boston, MA 02108'
        store_selection.set_store_as_preferred(store_address)
        self.assertTrue(confirmation_page.is_confirmation_displayed(store_address), f"Confirmation not displayed for store: {store_address}")

    def test_TC_2100_verify_preferred_store_confirmation(self):
        """
        Test Case 2100: SCRUM-15408 TS-001 TC-009
        Steps:
        1. Verify the preferred store is set
        2. Verify confirmation is displayed across the site
        """
        driver = self.driver
        home_page = HomePage(driver)
        confirmation_page = ConfirmationPage(driver)
        product_listing_page = ProductListingPage(driver)
        store_address = '375 Washington Street, Boston, MA 02108'

        # Step 1: Verify the preferred store is set on the homepage
        home_page.load()
        self.assertTrue(home_page.is_preferred_store_set(store_address), f"Preferred store '{store_address}' is not set on homepage.")
        self.assertTrue(confirmation_page.is_confirmation_displayed(store_address), f"Confirmation not displayed for store: {store_address} on homepage.")

        # Step 2: Verify confirmation is displayed across the site (e.g., product listing)
        product_listing_page.load()
        self.assertTrue(product_listing_page.is_preferred_store_set(store_address), f"Preferred store '{store_address}' is not set on product listing page.")
        self.assertTrue(product_listing_page.is_store_confirmation_displayed(store_address), f"Confirmation not displayed for store: {store_address} on product listing page.")

if __name__ == "__main__":
    unittest.main()
