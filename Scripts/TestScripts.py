# Selenium Python test scripts for Foot Locker store locator
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
from Pages.Homepage import Homepage
from Pages.StoreLocatorPopup import StoreLocatorPopup

class TestFootLockerStoreLocator:
    def setup_method(self):
        self.driver = webdriver.Chrome()
        self.driver.maximize_window()

    def teardown_method(self):
        self.driver.quit()

    def test_set_preferred_store_confirmation(self):
        # Test Case 2017: SCRUM-15408 TS-005 TC-001
        driver = self.driver
        driver.get('https://www.footlocker.com')
        time.sleep(2)
        # Click 'Find a Store' and 'Select My Store'
        find_store = driver.find_element(By.LINK_TEXT, 'Find a Store')
        find_store.click()
        time.sleep(1)
        select_my_store = driver.find_element(By.LINK_TEXT, 'Select My Store')
        select_my_store.click()
        time.sleep(2)
        # Enter 'Boston, MA' in 'Location' textbox
        location_box = driver.find_element(By.ID, 'location')
        location_box.clear()
        location_box.send_keys('Boston, MA')
        # Click 'Search for Stores'
        search_button = driver.find_element(By.XPATH, "//button[contains(text(),'Search for Stores')]")
        search_button.click()
        time.sleep(2)
        # Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'
        set_my_store = driver.find_element(By.XPATH, "//div[contains(text(),'375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class,'store-info')]//button[contains(text(),'Set My Store')]")
        set_my_store.click()
        time.sleep(2)
        # Verify confirmation indicator
        confirmation = driver.find_element(By.XPATH, "//div[contains(@class,'confirmation-indicator') or contains(text(),'store set')]")
        assert confirmation.is_displayed(), 'Confirmation indicator not displayed.'

    def test_preferred_store_persistence(self):
        # Test Case 2018: SCRUM-15408 TS-006 TC-001
        driver = self.driver
        driver.get('https://www.footlocker.com')
        time.sleep(2)
        # Click 'Find a Store' and 'Select My Store'
        find_store = driver.find_element(By.LINK_TEXT, 'Find a Store')
        find_store.click()
        time.sleep(1)
        select_my_store = driver.find_element(By.LINK_TEXT, 'Select My Store')
        select_my_store.click()
        time.sleep(2)
        # Enter 'Boston, MA' in 'Location' textbox
        location_box = driver.find_element(By.ID, 'location')
        location_box.clear()
        location_box.send_keys('Boston, MA')
        # Click 'Search for Stores'
        search_button = driver.find_element(By.XPATH, "//button[contains(text(),'Search for Stores')]")
        search_button.click()
        time.sleep(2)
        # Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'
        set_my_store = driver.find_element(By.XPATH, "//div[contains(text(),'375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class,'store-info')]//button[contains(text(),'Set My Store')]")
        set_my_store.click()
        time.sleep(2)
        # Navigate to Men's Sneakers
        driver.get('https://www.footlocker.com/men/shoes')
        time.sleep(2)
        # Verify preferred store persists
        preferred_store = driver.find_element(By.XPATH, "//div[contains(@class,'preferred-store') and contains(text(),'375 Washington Street, Boston, MA 02108')]")
        assert preferred_store.is_displayed(), 'Preferred store not visible after navigation.'

    def test_homepage_find_store_popup(self):
        # Test Case 2073: SCRUM-15408 TS-001 TC-001
        driver = self.driver
        homepage = Homepage(driver)
        homepage.load_homepage()
        homepage.click_find_store()
        popup = StoreLocatorPopup(driver)
        popup.wait_for_popup()
        popup.verify_popup_message()
        popup.verify_select_my_store_button()

    def test_store_locator_popup_fields(self):
        # Test Case 2074: SCRUM-15408 TS-001 TC-002
        driver = self.driver
        homepage = Homepage(driver)
        homepage.load_homepage()
        homepage.click_find_store()
        popup = StoreLocatorPopup(driver)
        popup.wait_for_popup()
        popup.click_select_my_store()
        popup.verify_location_textbox_and_search_button()

    def test_store_locator_search_boston(self):
        # Test Case 2075: SCRUM-15408 TS-001 TC-003
        driver = self.driver
        homepage = Homepage(driver)
        homepage.load_homepage()
        homepage.click_find_a_store()
        popup = StoreLocatorPopup(driver)
        popup.wait_for_popup()
        popup.click_select_my_store()
        popup.enter_location('Boston, MA')
        popup.click_search_for_stores()
        assert popup.verify_store_search_results('Boston'), "Store locator popup did not display results for stores in or near Boston."

    def test_store_locator_verify_address(self):
        # Test Case 2076: SCRUM-15408 TS-001 TC-004
        driver = self.driver
        homepage = Homepage(driver)
        homepage.load_homepage()
        homepage.click_find_a_store()
        popup = StoreLocatorPopup(driver)
        popup.wait_for_popup()
        popup.click_select_my_store()
        popup.enter_location('Boston, MA')
        popup.click_search_for_stores()
        assert popup.verify_store_search_results('375 Washington Street, Boston, MA 02108'), "Store with address '375 Washington Street, Boston, MA 02108' is not visible in the results."

    def test_homepage_find_store_popup_fields(self):
        # Test Case 2081: SCRUM-15408 TS-001 TC-002
        driver = self.driver
        homepage = Homepage(driver)
        homepage.load_homepage()
        assert homepage.load_homepage(), "Homepage did not load successfully."
        assert homepage.click_find_store_link(), "Could not click 'Find a Store' link."
        popup = StoreLocatorPopup(driver)
        assert popup.wait_for_popup(), "Store locator popup did not appear."
        assert popup.click_select_my_store(), "Could not click 'Select My Store' button."
        assert popup.verify_location_textbox_and_search_button(), "Location textbox or Search for Stores button not visible in popup."

    def test_store_locator_search_boston_ma(self):
        # Test Case 2082: SCRUM-15408 TS-002 TC-001
        driver = self.driver
        homepage = Homepage(driver)
        homepage.load_homepage()
        assert homepage.load_homepage(), "Homepage did not load successfully."
        assert homepage.click_find_store_link(), "Could not click 'Find a Store' link."
        popup = StoreLocatorPopup(driver)
        assert popup.wait_for_popup(), "Store locator popup did not appear."
        assert popup.click_select_my_store(), "Could not click 'Select My Store' button."
        assert popup.enter_location('Boston, MA'), "Could not enter location 'Boston, MA'."
        assert popup.click_search_for_stores(), "Could not click 'Search for Stores' button."
        # If verify_store_search_results exists, add:
        # assert popup.verify_store_search_results('Boston'), "Store locator popup did not display results for stores in or near Boston."
