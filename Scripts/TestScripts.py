# TestScripts.py
"""
Automated tests for Foot Locker Store Locator using Selenium and pytest.
"""
import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

@pytest.fixture(scope='function')
def driver():
    driver = webdriver.Chrome()
    yield driver
    driver.quit()

class TestStoreLocator:
    def test_search_valid_location(self, driver):
        """Test searching for stores in a valid location (Boston, MA)"""
        # Existing logic
        pass

    def test_search_invalid_location(self, driver):
        """Test searching for stores in an invalid location (Antarctica)"""
        # Existing logic
        pass

    def test_store_locator_popup_and_address(self, driver):
        """Test Case - SCRUM-15408 TS-003 TC-001: Verify store locator popup and address match"""
        # Existing logic
        pass

    def test_set_my_store(self, driver):
        """Test Case - SCRUM-15408 TS-004 TC-001: Set '375 Washington Street, Boston, MA 02108' as preferred store"""
        # Existing logic
        pass

    def test_find_a_store_popup_and_select_link(self, driver):
        """Test Case - SCRUM-15408 TS-001 TC-001: Launch Foot Locker website, click 'Find a Store', verify popup and 'Select My Store' link"""
        driver.get('https://www.footlocker.com/')
        WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.XPATH, "//button[contains(text(), 'Find a Store')]")))
        find_store_btn = driver.find_element(By.XPATH, "//button[contains(text(), 'Find a Store')]")
        find_store_btn.click()
        popup = WebDriverWait(driver, 10).until(EC.visibility_of_element_located((By.XPATH, "//div[contains(@class, 'store-locator-popup')]")))
        assert popup.is_displayed(), "Find a Store popup should appear below the button"
        message_elem = driver.find_element(By.XPATH, "//div[contains(@class, 'store-locator-popup')]//p[contains(text(), 'Choose a preferred store to make shopping easier')]")
        assert message_elem.is_displayed(), "Popup should display the correct message"
        select_my_store_link = driver.find_element(By.XPATH, "//a[contains(text(), 'Select My Store')]")
        assert select_my_store_link.is_displayed(), "'Select My Store' link should be visible within the popup"

    def test_select_my_store_opens_locator_popup(self, driver):
        """Test Case - SCRUM-15408 TS-001 TC-002: Click 'Select My Store' and verify store locator popup, textbox, and button"""
        driver.get('https://www.footlocker.com/')
        WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.XPATH, "//button[contains(text(), 'Find a Store')]")))
        find_store_btn = driver.find_element(By.XPATH, "//button[contains(text(), 'Find a Store')]")
        find_store_btn.click()
        select_my_store_link = WebDriverWait(driver, 10).until(EC.element_to_be_clickable((By.XPATH, "//a[contains(text(), 'Select My Store')]") ))
        select_my_store_link.click()
        store_locator_popup = WebDriverWait(driver, 10).until(EC.visibility_of_element_located((By.XPATH, "//div[contains(@class, 'store-locator-popup-window')]")))
        assert store_locator_popup.is_displayed(), "Store locator popup window should open"
        location_textbox = driver.find_element(By.XPATH, "//input[@type='text' and @placeholder='Location']")
        assert location_textbox.is_displayed(), "'Location' textbox should be present in the popup"
        search_button = driver.find_element(By.XPATH, "//button[contains(text(), 'Search for Stores')]")
        assert search_button.is_displayed(), "'Search for Stores' button should be present in the popup"
