import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

# ... (existing code and methods above remain unchanged)

class FootLockerStoreLocatorTests(unittest.TestCase):
    # ... (existing methods remain unchanged)

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
            # Locate 'Find a Store' link in header
            find_store_link = WebDriverWait(driver, 10).until(
                EC.element_to_be_clickable((By.LINK_TEXT, "Find a Store"))
            )
            find_store_link.click()

            # Verify popup appears (assume popup has class 'store-locator-popup')
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
            # Locate and click 'Find a Store' link in header
            find_store_link = WebDriverWait(driver, 10).until(
                EC.element_to_be_clickable((By.LINK_TEXT, "Find a Store"))
            )
            find_store_link.click()

            # Verify popup message
            popup = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.CLASS_NAME, "store-locator-popup"))
            )
            message = popup.find_element(By.XPATH, ".//p[contains(text(), 'Choose a preferred store to make shopping easier')]")
            self.assertIsNotNone(message, "Expected popup message not found.")

            # Verify 'Select My Store' link exists
            select_store_link = popup.find_element(By.LINK_TEXT, "Select My Store")
            self.assertTrue(select_store_link.is_displayed(), "'Select My Store' link not found in popup.")

        finally:
            driver.quit()

# ... (existing code, if any, below remains unchanged)