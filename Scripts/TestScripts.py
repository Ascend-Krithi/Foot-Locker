# Existing imports and code remain unchanged
import unittest
from selenium import webdriver
from PageClasses.HomePage import HomePage
from PageClasses.StoreLocatorPopup import StoreLocatorPopup
from PageClasses.StoreSelectionPopup import StoreSelectionPopup
from PageClasses.ConfirmationPage import ConfirmationPage
from PageClasses.ProductListingPage import ProductListingPage

class TestScripts(unittest.TestCase):
    # ... Existing test methods remain unchanged ...
    ...

    def test_2103_homepage_find_store_popup(self):
        """
        TestCaseId: 2103
        Step 1: Launch 'https://www.footlocker.com/' and validate the Home Page is displayed.
        Step 2: Validate 'Find a Store' link is visible.
        Step 3: Click 'Find a Store', validate Store Locator popup appears.
        """
        driver = webdriver.Chrome()
        try:
            driver.get('https://www.footlocker.com/')
            homepage = HomePage(driver)
            self.assertTrue(homepage.is_homepage_loaded(), "Home Page did not load successfully.")
            self.assertTrue(homepage.is_find_a_store_visible(), "'Find a Store' link is not visible.")
            homepage.click_find_a_store()
            store_locator_popup = StoreLocatorPopup(driver)
            self.assertTrue(store_locator_popup.is_popup_visible(), "Store Locator popup did not appear after clicking 'Find a Store'.")
        finally:
            driver.quit()

    def test_2104_store_locator_popup_message(self):
        """
        TestCaseId: 2104
        Step 1: Launch 'https://www.footlocker.com/' and validate Home Page is displayed.
        Step 2: Click 'Find a Store', validate Store Locator popup appears.
        Step 3: Validate popup message 'Choose a preferred store to make shopping easier' is visible.
        """
        driver = webdriver.Chrome()
        try:
            driver.get('https://www.footlocker.com/')
            homepage = HomePage(driver)
            self.assertTrue(homepage.is_homepage_loaded(), "Home Page did not load successfully.")
            homepage.click_find_a_store()
            store_locator_popup = StoreLocatorPopup(driver)
            self.assertTrue(store_locator_popup.is_popup_visible(), "Store Locator popup did not appear after clicking 'Find a Store'.")
            self.assertTrue(store_locator_popup.is_choose_preferred_store_message_visible(), "Popup message 'Choose a preferred store to make shopping easier' is not visible.")
        finally:
            driver.quit()
