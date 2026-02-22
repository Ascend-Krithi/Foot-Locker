import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class FootLockerStoreLocatorTests(unittest.TestCase):
    def test_launch_and_find_store_popup_appears(self):
        """
        TestCase 2103:
        1. Launch Foot Locker website.
        2. Locate 'Find a Store' link in header.
        3. Click 'Find a Store'.
        4. Verify popup appears.
        """
        driver = webdriver.Chrome()
        driver.get("https://www.footlocker.com/")
        try:
            find_store_link = WebDriverWait(driver, 10).until(
                EC.element_to_be_clickable((By.LINK_TEXT, "Find a Store"))
            )
            find_store_link.click()
            popup = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.CLASS_NAME, "store-locator-popup"))
            )
            self.assertTrue(popup.is_displayed(), "Store locator popup did not appear after clicking 'Find a Store'.")
        finally:
            driver.quit()

    def test_find_store_popup_message_and_link(self):
        """
        TestCase 2104:
        1. Launch Foot Locker website.
        2. Click 'Find a Store' in header.
        3. Verify popup displays message 'Choose a preferred store to make shopping easier' and a 'Select My Store' link.
        """
        driver = webdriver.Chrome()
        driver.get("https://www.footlocker.com/")
        try:
            find_store_link = WebDriverWait(driver, 10).until(
                EC.element_to_be_clickable((By.LINK_TEXT, "Find a Store"))
            )
            find_store_link.click()
            popup = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.CLASS_NAME, "store-locator-popup"))
            )
            message = popup.find_element(By.XPATH, ".//p[contains(text(), 'Choose a preferred store to make shopping easier')]")
            self.assertIsNotNone(message, "Expected popup message not found.")
            select_store_link = popup.find_element(By.LINK_TEXT, "Select My Store")
            self.assertTrue(select_store_link.is_displayed(), "'Select My Store' link not found in popup.")
        finally:
            driver.quit()

    def test_store_locator_valid_location(self):
        """
        TestCase 2107:
        1. Launch the Foot Locker website and navigate to the Store Locator page.
        2. Enter 'Massachusetts' in the Location textbox.
        3. Click on the 'Search for Stores' button.
        Expected: List of stores in Massachusetts is displayed.
        """
        driver = webdriver.Chrome()
        driver.get("https://www.footlocker.com/store-locator")
        try:
            # Enter location
            location_box = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.ID, "store-locator-input"))
            )
            location_box.clear()
            location_box.send_keys("Massachusetts")

            # Click search button
            search_button = WebDriverWait(driver, 10).until(
                EC.element_to_be_clickable((By.ID, "store-locator-search"))
            )
            search_button.click()

            # Verify list of stores is displayed
            stores_list = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.ID, "stores-list"))
            )
            self.assertTrue(stores_list.is_displayed(), "Store list for Massachusetts is not displayed.")
        finally:
            driver.quit()

    def test_store_locator_invalid_location(self):
        """
        TestCase 2108:
        1. Launch the Foot Locker website and navigate to the Store Locator page.
        2. Enter 'InvalidCity123' in the Location textbox.
        3. Click on the 'Search for Stores' button.
        Expected: Error message is displayed indicating no stores found.
        """
        driver = webdriver.Chrome()
        driver.get("https://www.footlocker.com/store-locator")
        try:
            # Enter invalid location
            location_box = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.ID, "store-locator-input"))
            )
            location_box.clear()
            location_box.send_keys("InvalidCity123")

            # Click search button
            search_button = WebDriverWait(driver, 10).until(
                EC.element_to_be_clickable((By.ID, "store-locator-search"))
            )
            search_button.click()

            # Verify error message is displayed
            error_message = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.ID, "store-locator-error"))
            )
            self.assertTrue(error_message.is_displayed(), "Error message for invalid location is not displayed.")
        finally:
            driver.quit()
