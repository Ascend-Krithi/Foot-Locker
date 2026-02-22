# HomePage PageClass for Foot Locker Website
# Strict Selenium Python best practices and comprehensive documentation
import json
import os
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class HomePage:
    """
    HomePage PageClass
    ------------------
    Provides methods to load the homepage and navigate to Store Locator.

    Executive Summary:
    - Automates navigation to Store Locator via homepage link.
    - Uses Locators.json for locator mapping.
    - Ensures robust waits and exception handling.

    Implementation Guide:
    - Call load_homepage() to open the homepage.
    - Call click_find_a_store() to go to Store Locator.

    QA Report:
    - Locators validated against Locators.json.
    - Waits ensure elements are present/clickable.
    - Atomic, reusable methods.

    Troubleshooting Guide:
    - Update Locators.json if homepage structure changes.
    - Check browser logs for navigation issues.

    Future Considerations:
    - Expand to handle homepage banners/popups.
    - Add logging for navigation actions.
    """
    def __init__(self, driver):
        self.driver = driver
        with open(os.path.join(os.path.dirname(__file__), '../Locators/Locators.json')) as f:
            self.locators = json.load(f)

    def load_homepage(self, url='https://www.footlocker.com/'):
        """
        Loads the Foot Locker homepage and waits for 'Find a Store' link.
        Args:
            url (str): Homepage URL.
        Returns:
            bool: True if homepage loaded and link present, False otherwise.
        """
        self.driver.get(url)
        try:
            WebDriverWait(self.driver, 10).until(
                EC.presence_of_element_located((
                    getattr(By, self.locators['homepage_find_store_link']['by'].upper()),
                    self.locators['homepage_find_store_link']['value']
                ))
            )
            return True
        except Exception:
            return False

    def click_find_a_store(self):
        """
        Clicks the 'Find a Store' link on the homepage.
        Returns:
            bool: True if link clicked successfully, False otherwise.
        """
        try:
            find_store = WebDriverWait(self.driver, 10).until(
                EC.element_to_be_clickable((
                    getattr(By, self.locators['homepage_find_store_link']['by'].upper()),
                    self.locators['homepage_find_store_link']['value']
                ))
            )
            find_store.click()
            return True
        except Exception:
            return False
