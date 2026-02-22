import unittest
from StoreLocatorPage import StoreLocatorPage
from MensSneakersPage import MensSneakersPage
from WomensShoesPage import WomensShoesPage
from selenium import webdriver

class TestScripts(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.page = StoreLocatorPage(cls.driver)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    # Existing test methods ...
    def test_scrum_15408_ts_004_tc_002_set_other_and_preferred_store(self): ...
    def test_scrum_15408_ts_005_tc_001_set_preferred_store_with_confirmation(self): ...
    def test_scrum_15408_ts_005_tc_002_store_locator_and_mens_page(self): ...
    def test_scrum_15408_ts_006_tc_001_store_locator_and_womens_page(self): ...
    def test_scrum_15408_ts_001_tc_002_store_locator_location_textbox(self): ...
    def test_scrum_15408_ts_001_tc_003_store_locator_search_boston(self): ...

    def test_scrum_15408_ts_001_tc_008_set_preferred_store(self): ...
    def test_scrum_15408_ts_001_tc_009_verify_preferred_store_confirmation(self): ...

    def test_scrum_15408_ts_001_tc_010_preferred_store_persistence_navigation(self):
        """
        SCRUM-15408 TS-001 TC-010
        1. Set the store at '375 Washington Street, Boston, MA 02108' as 'My Store'.
        2. Navigate to another page (Men's Shoes).
        3. Return to the homepage.
        4. Click 'Find a Store' and 'Select My Store' again.
        5. Verify the preferred store is still set.
        """
        store_locator = StoreLocatorPage(self.driver)
        mens_sneakers = MensSneakersPage(self.driver)

        # Step 1: Open homepage and set preferred store
        store_locator.open_homepage()
        store_locator.click_find_store()
        store_locator.enter_location('375 Washington Street, Boston, MA 02108')
        store_locator.click_search_for_stores()
        store_locator.set_my_store('375 Washington Street, Boston, MA 02108')
        self.assertTrue(store_locator.is_confirmation_displayed(), "Confirmation not displayed after setting preferred store.")

        # Step 2: Navigate to Men's Shoes page
        mens_sneakers.launch_page()
        self.assertTrue(mens_sneakers.verify_store_indicator('375 Washington Street, Boston, MA 02108'), "Preferred store not indicated on Men's Sneakers page.")

        # Step 3: Return to homepage
        store_locator.open_homepage()

        # Step 4: Click 'Find a Store' and 'Select My Store' again
        store_locator.click_find_store()
        store_locator.click_select_my_store()

        # Step 5: Verify the preferred store is still set
        self.assertTrue(store_locator.is_my_store_persisted('375 Washington Street, Boston, MA 02108'), "Preferred store was not persisted after navigation.")

    def test_scrum_15408_ts_001_tc_011_preferred_store_persistence_browser_restart(self):
        """
        SCRUM-15408 TS-001 TC-011
        1. Set the store at '375 Washington Street, Boston, MA 02108' as 'My Store'.
        2. Close the browser completely.
        3. Reopen browser and navigate to homepage.
        4. Click 'Find a Store' and 'Select My Store' again.
        5. Verify the preferred store is still set.
        """
        store_locator = StoreLocatorPage(self.driver)

        # Step 1: Open homepage and set preferred store
        store_locator.open_homepage()
        store_locator.click_find_store()
        store_locator.enter_location('375 Washington Street, Boston, MA 02108')
        store_locator.click_search_for_stores()
        store_locator.set_my_store('375 Washington Street, Boston, MA 02108')
        self.assertTrue(store_locator.is_confirmation_displayed(), "Confirmation not displayed after setting preferred store.")

        # Step 2: Close the browser completely
        self.driver.quit()

        # Step 3: Reopen browser and navigate to homepage
        self.driver = webdriver.Chrome()
        store_locator = StoreLocatorPage(self.driver)
        store_locator.open_homepage()

        # Step 4: Click 'Find a Store' and 'Select My Store' again
        store_locator.click_find_store()
        store_locator.click_select_my_store()

        # Step 5: Verify the preferred store is still set
        self.assertTrue(store_locator.is_my_store_persisted('375 Washington Street, Boston, MA 02108'), "Preferred store was not persisted after browser restart.")

    def test_scrum_15408_ts_sl_001_tc_001_store_locator_popup(self):
        """
        SCRUM-15408 TS-SL-001 TC-001
        1. Launch the Foot Locker website in a browser.
        2. Locate the 'Find a Store' link in the header.
        3. Click on the 'Find a Store' link.
        Expected: Home Page is displayed, 'Find a Store' link is visible, Store Locator popup appears below the link.
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.open_homepage("https://www.footlocker.com/")
        # Step 2: Implicit in open_homepage (waits for 'Find a Store' link)
        # Step 3: Click 'Find a Store' link
        store_locator.click_find_store()
        # Verify Store Locator popup appears
        self.assertTrue(store_locator.verify_store_locator_popup(), "Store Locator popup did not appear below the link.")

    def test_scrum_15408_ts_sl_001_tc_002_store_locator_popup_message_and_links(self):
        """
        SCRUM-15408 TS-SL-001 TC-002
        1. Launch the Foot Locker website in a browser.
        2. Click on the 'Find a Store' link in the header.
        3. Observe the popup message and available links.
        Expected: Popup displays message 'Choose a preferred store to make shopping easier' and a 'Select My Store' link.
        """
        store_locator = StoreLocatorPage(self.driver)
        store_locator.open_homepage("https://www.footlocker.com/")
        store_locator.click_find_store()
        self.assertTrue(store_locator.verify_popup_message_and_links(), "Popup message or 'Select My Store' link not found or not displayed.")
