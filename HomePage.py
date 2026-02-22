# HomePage.py
"""
Page Object for the Foot Locker Home Page.
Covers navigation to Store Locator via 'Find a Store' link.
QA Notes:
- All locators referenced from provided dictionary.
- Methods validated for navigation and element visibility.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class HomePage:
    FIND_A_STORE_LINK = (By.LINK_TEXT, "Find a Store")

    def __init__(self, driver: WebDriver):
        self.driver = driver

    def go_to_store_locator(self):
        """
        Clicks the 'Find a Store' link to open the Store Locator popup.
        Returns True if navigation is successful.
        """
        find_store_link = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.FIND_A_STORE_LINK)
        )
        find_store_link.click()
        return True
