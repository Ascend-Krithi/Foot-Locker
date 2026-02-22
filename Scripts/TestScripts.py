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

    def test_store_locator_search_boston_map_view(self):
        """
        TestCase 2111:
        1. Launch the Foot Locker website and navigate to the Store Locator page. [URL: https://www.footlocker.com/store-locator]
           - Verify Store Locator page is displayed.
        2. Perform a search for 'Boston, MA'.
           - Verify list of stores in Boston is displayed.
        3. Toggle to the map view.
           - Verify map is displayed with pins for each store location.
        """
        driver = webdriver.Chrome()
        driver.get("https://www.footlocker.com/store-locator")
        try:
            # Verify Store Locator page is displayed
            locator_header = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.XPATH, "//h1[contains(text(), 'Store Locator')]"))
            )
            self.assertTrue(locator_header.is_displayed(), "Store Locator page header not found.")

            # Find the search input and enter 'Boston, MA'
            search_input = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.ID, "store-locator-search-input"))
            )
            search_input.clear()
            search_input.send_keys("Boston, MA")

            # Click the search button
            search_button = driver.find_element(By.XPATH, "//button[contains(@aria-label, 'Search')]" )
            search_button.click()

            # Wait for results to load, verify stores in Boston are displayed
            results_list = WebDriverWait(driver, 10).until(
                EC.presence_of_element_located((By.CSS_SELECTOR, "ul.store-list, div.store-list"))
            )
            store_items = results_list.find_elements(By.XPATH, ".//li[contains(., 'Boston') or .//span[contains(., 'Boston')]]")
            self.assertTrue(len(store_items) > 0, "No store results for 'Boston, MA' found.")

            # Toggle to map view (look for a button or tab labeled 'Map')
            map_tab = WebDriverWait(driver, 10).until(
                EC.element_to_be_clickable((By.XPATH, "//button[contains(., 'Map')] | //a[contains(., 'Map')]"))
            )
            map_tab.click()

            # Verify map is displayed with pins for each store location
            map_element = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.CSS_SELECTOR, "div.store-locator-map, #store-locator-map, .map-container"))
            )
            # Pins: look for elements with a class like 'map-pin', 'store-marker', or use SVGs
            pins = map_element.find_elements(By.CSS_SELECTOR, ".map-pin, .store-marker, svg [aria-label*='Store']")
            self.assertTrue(len(pins) > 0, "No map pins found for store locations in map view.")
        finally:
            driver.quit()

    def test_store_locator_search_newyork_pagination(self):
        """
        TestCase 2112:
        1. Launch the Foot Locker website and navigate to the Store Locator page. [URL: https://www.footlocker.com/store-locator]
           - Verify Store Locator page is displayed.
        2. Perform a search that returns more stores than fit on one page (e.g., 'New York').
           - Verify multiple pages of store results are displayed.
        3. Use the pagination controls to navigate to the next page.
           - Verify next page of store results is displayed.
        """
        driver = webdriver.Chrome()
        driver.get("https://www.footlocker.com/store-locator")
        try:
            # Verify Store Locator page is displayed
            locator_header = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.XPATH, "//h1[contains(text(), 'Store Locator')]"))
            )
            self.assertTrue(locator_header.is_displayed(), "Store Locator page header not found.")

            # Find the search input and enter 'New York'
            search_input = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.ID, "store-locator-search-input"))
            )
            search_input.clear()
            search_input.send_keys("New York")

            # Click the search button
            search_button = driver.find_element(By.XPATH, "//button[contains(@aria-label, 'Search')]" )
            search_button.click()

            # Wait for results to load, verify stores in New York are displayed
            results_list = WebDriverWait(driver, 10).until(
                EC.presence_of_element_located((By.CSS_SELECTOR, "ul.store-list, div.store-list"))
            )
            store_items = results_list.find_elements(By.XPATH, ".//li[contains(., 'New York') or .//span[contains(., 'New York')]]")
            self.assertTrue(len(store_items) > 0, "No store results for 'New York' found.")

            # Look for pagination controls (e.g., 'Next', page numbers, etc.)
            pagination = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.CSS_SELECTOR, ".pagination, nav[aria-label='Pagination'], ul.pagination"))
            )
            page_buttons = pagination.find_elements(By.XPATH, ".//button | .//a")
            self.assertTrue(len(page_buttons) > 1, "Pagination controls not found or only one page of results.")

            # Capture first store on page 1
            first_store_name = store_items[0].text if store_items else None

            # Click 'Next' or page 2
            next_btn = None
            for btn in page_buttons:
                if btn.text.strip().lower() in ('next', '>') or btn.get_attribute('aria-label') == 'Next page':
                    next_btn = btn
                    break
            if not next_btn:
                # Fallback: try to find page 2
                for btn in page_buttons:
                    if btn.text.strip() == '2':
                        next_btn = btn
                        break
            self.assertIsNotNone(next_btn, "Next page button not found in pagination controls.")
            next_btn.click()

            # Wait for results to update
            WebDriverWait(driver, 10).until(
                EC.staleness_of(store_items[0])
            )
            results_list_2 = WebDriverWait(driver, 10).until(
                EC.presence_of_element_located((By.CSS_SELECTOR, "ul.store-list, div.store-list"))
            )
            store_items_2 = results_list_2.find_elements(By.XPATH, ".//li[contains(., 'New York') or .//span[contains(., 'New York')]]")
            self.assertTrue(len(store_items_2) > 0, "No store results found on next page.")
            if first_store_name:
                self.assertNotEqual(store_items_2[0].text, first_store_name, "Store results did not change after pagination.")
        finally:
            driver.quit()

# ... (existing code, if any, below remains unchanged)
