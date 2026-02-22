# HomePage.py
"""
Page Object for Foot Locker Home Page.
Covers homepage load, header links, and navigation.
Test Steps Covered:
- Launch Foot Locker website and verify Home Page is displayed.
- Locate and click 'Find a Store' link in header.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class HomePage:
    """
    Page Object representing the Foot Locker Home Page.
    """
    FIND_A_STORE_LINK = (By.LINK_TEXT, "Find a Store")

    def __init__(self, driver):
        """
        Initializes HomePage object.
        :param driver: Selenium WebDriver instance
        """
        self.driver = driver

    def is_displayed(self, timeout=10):
        """
        Verifies that the Home Page is loaded by checking for the presence of the 'Find a Store' link.
        :param timeout: Max seconds to wait
        :return: True if displayed, False otherwise
        """
        try:
            WebDriverWait(self.driver, timeout).until(
                EC.visibility_of_element_located(self.FIND_A_STORE_LINK)
            )
            return True
        except Exception:
            return False

    def is_find_a_store_link_visible(self, timeout=10):
        """
        Checks if the 'Find a Store' link is visible in the header.
        :param timeout: Max seconds to wait
        :return: True if visible, False otherwise
        """
        try:
            WebDriverWait(self.driver, timeout).until(
                EC.visibility_of_element_located(self.FIND_A_STORE_LINK)
            )
            return True
        except Exception:
            return False

    def click_find_a_store_link(self, timeout=10):
        """
        Clicks the 'Find a Store' link in the header.
        :param timeout: Max seconds to wait
        :return: None
        """
        WebDriverWait(self.driver, timeout).until(
            EC.element_to_be_clickable(self.FIND_A_STORE_LINK)
        ).click()
