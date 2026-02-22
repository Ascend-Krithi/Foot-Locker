# Existing imports and test methods are preserved
import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from Pages.HomePage import HomePage
from Pages.FindAStorePopup import FindAStorePopup
from Pages.StoreSearchResults import StoreSearchResults

class TestStoreSelection(unittest.TestCase):
    # ...existing test methods...

    def test_2101_set_my_store_and_verify_persistence(self):
        """
        TestCase 2101:
        - Set '375 Washington Street, Boston, MA 02108' as 'My Store'
        - Close and reopen browser
        - Verify 'My Store' is persisted
        """
        driver = webdriver.Chrome()
        wait = WebDriverWait(driver, 20)
        try:
            # Step 1: Navigate to Home Page
            driver.get("https://your-app-url.com")
            home_page = HomePage(driver)
            # Step 2: Open Find A Store popup
            home_page.click_find_a_store()
            popup = FindAStorePopup(driver)
            wait.until(EC.visibility_of_element_located((By.ID, popup.SEARCH_BOX_ID)))
            # Step 3: Search for the address
            popup.enter_search_text("375 Washington Street, Boston, MA 02108")
            popup.click_search_button()
            results = StoreSearchResults(driver)
            wait.until(EC.visibility_of_element_located((By.XPATH, results.get_store_result_xpath("375 Washington Street"))))
            # Step 4: Set as My Store
            results.click_set_as_my_store("375 Washington Street")
            # Step 5: Validate 'My Store' is set
            self.assertTrue(home_page.is_my_store_set("375 Washington Street"))
            # Step 6: Close browser
            driver.quit()
            # Step 7: Reopen browser
            driver = webdriver.Chrome()
            wait = WebDriverWait(driver, 20)
            driver.get("https://your-app-url.com")
            home_page = HomePage(driver)
            # Step 8: Validate 'My Store' is persisted
            self.assertTrue(home_page.is_my_store_set("375 Washington Street"))
        finally:
            driver.quit()

    def test_2102_my_store_navigation_and_reopen(self):
        """
        TestCase 2102:
        - Set '375 Washington Street, Boston, MA 02108' as 'My Store'
        - Navigate to a different page
        - Return to Home and verify 'My Store' is still set
        - Close and reopen browser, verify persistence
        """
        driver = webdriver.Chrome()
        wait = WebDriverWait(driver, 20)
        try:
            # Step 1: Navigate to Home Page
            driver.get("https://your-app-url.com")
            home_page = HomePage(driver)
            # Step 2: Open Find A Store popup
            home_page.click_find_a_store()
            popup = FindAStorePopup(driver)
            wait.until(EC.visibility_of_element_located((By.ID, popup.SEARCH_BOX_ID)))
            # Step 3: Search for the address
            popup.enter_search_text("375 Washington Street, Boston, MA 02108")
            popup.click_search_button()
            results = StoreSearchResults(driver)
            wait.until(EC.visibility_of_element_located((By.XPATH, results.get_store_result_xpath("375 Washington Street"))))
            # Step 4: Set as My Store
            results.click_set_as_my_store("375 Washington Street")
            # Step 5: Navigate to another page (e.g., /about)
            driver.get("https://your-app-url.com/about")
            # Step 6: Return to Home
            driver.get("https://your-app-url.com")
            home_page = HomePage(driver)
            # Step 7: Validate 'My Store' is still set
            self.assertTrue(home_page.is_my_store_set("375 Washington Street"))
            # Step 8: Close browser
            driver.quit()
            # Step 9: Reopen browser
            driver = webdriver.Chrome()
            wait = WebDriverWait(driver, 20)
            driver.get("https://your-app-url.com")
            home_page = HomePage(driver)
            # Step 10: Validate 'My Store' is persisted
            self.assertTrue(home_page.is_my_store_set("375 Washington Street"))
        finally:
            driver.quit()
