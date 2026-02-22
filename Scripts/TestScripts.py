# Existing imports and code remain unchanged
import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

class TestScripts(unittest.TestCase):
    # ... (existing test methods, e.g., test_search_boston_store)

    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)
        self.base_url = "https://www.example.com"  # Replace with actual URL

    def tearDown(self):
        self.driver.quit()

    def test_set_my_store_boston_ma_2017(self):
        driver = self.driver
        driver.get(self.base_url)

        # Click 'Find a Store'
        find_store_btn = driver.find_element(By.LINK_TEXT, "Find a Store")
        find_store_btn.click()

        # Click 'Select My Store'
        select_my_store_btn = driver.find_element(By.XPATH, "//button[contains(text(), 'Select My Store')]")
        select_my_store_btn.click()

        # Enter 'Boston, MA' and search
        location_input = driver.find_element(By.ID, "store-locator-search-input")
        location_input.clear()
        location_input.send_keys("Boston, MA")
        location_input.send_keys(Keys.RETURN)

        # Wait for results and click 'Set My Store' for '375 Washington Street, Boston, MA 02108'
        wait = WebDriverWait(driver, 10)
        store_card = wait.until(EC.presence_of_element_located((
            By.XPATH,
            "//div[contains(@class, 'store-address') and contains(., '375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class, 'store-card')]"
        )))
        set_my_store_btn = store_card.find_element(By.XPATH, ".//button[contains(text(), 'Set My Store')]")
        set_my_store_btn.click()

        # Verify confirmation message appears
        confirmation = wait.until(EC.visibility_of_element_located((
            By.XPATH,
            "//div[contains(@class, 'store-confirmation') and contains(., 'Your preferred store is set to 375 Washington Street')]"
        )))
        self.assertIn("Your preferred store is set to 375 Washington Street", confirmation.text)

        # Refresh and verify store persists
        driver.refresh()
        preferred_store_banner = wait.until(EC.visibility_of_element_located((
            By.XPATH,
            "//div[contains(@class, 'preferred-store') and contains(., '375 Washington Street, Boston, MA 02108')]"
        )))
        self.assertIn("375 Washington Street, Boston, MA 02108", preferred_store_banner.text)

    def test_store_persistence_on_mens_sneakers_2018(self):
        driver = self.driver
        driver.get(self.base_url)

        # Click 'Find a Store'
        find_store_btn = driver.find_element(By.LINK_TEXT, "Find a Store")
        find_store_btn.click()

        # Click 'Select My Store'
        select_my_store_btn = driver.find_element(By.XPATH, "//button[contains(text(), 'Select My Store')]")
        select_my_store_btn.click()

        # Enter 'Boston, MA' and search
        location_input = driver.find_element(By.ID, "store-locator-search-input")
        location_input.clear()
        location_input.send_keys("Boston, MA")
        location_input.send_keys(Keys.RETURN)

        # Wait for results and click 'Set My Store' for '375 Washington Street, Boston, MA 02108'
        wait = WebDriverWait(driver, 10)
        store_card = wait.until(EC.presence_of_element_located((
            By.XPATH,
            "//div[contains(@class, 'store-address') and contains(., '375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class, 'store-card')]"
        )))
        set_my_store_btn = store_card.find_element(By.XPATH, ".//button[contains(text(), 'Set My Store')]")
        set_my_store_btn.click()

        # Verify confirmation message appears
        confirmation = wait.until(EC.visibility_of_element_located((
            By.XPATH,
            "//div[contains(@class, 'store-confirmation') and contains(., 'Your preferred store is set to 375 Washington Street')]"
        )))
        self.assertIn("Your preferred store is set to 375 Washington Street", confirmation.text)

        # Navigate to Men's Sneakers page
        mens_menu = driver.find_element(By.LINK_TEXT, "Men")
        mens_menu.click()
        sneakers_link = wait.until(EC.element_to_be_clickable((By.LINK_TEXT, "Sneakers")))
        sneakers_link.click()

        # Verify preferred store remains set and visible
        preferred_store_banner = wait.until(EC.visibility_of_element_located((
            By.XPATH,
            "//div[contains(@class, 'preferred-store') and contains(., '375 Washington Street, Boston, MA 02108')]"
        )))
        self.assertIn("375 Washington Street, Boston, MA 02108", preferred_store_banner.text)

# If this is a standalone script, add:
# if __name__ == "__main__":
#     unittest.main()
