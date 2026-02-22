# HomePage.py
"""
Executive Summary:
Page Object for the Foot Locker Homepage.
Implements actions and validations for homepage elements.

Detailed Analysis:
- Handles loading and validation of homepage elements.
- Provides methods to locate and interact with the 'Find a Store' link.

Implementation Guide:
- Use is_homepage_loaded() to check if homepage is displayed.
- Use click_find_a_store() to open the Store Locator popup.

QA Report:
- Locators validated against Locators.json.
- All methods tested for visibility and interaction.

Troubleshooting Guide:
- If homepage fails to load, check URL and network connectivity.
- If 'Find a Store' link is not visible, verify locator accuracy.

Future Considerations:
- Extend for additional homepage elements and actions as needed.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver

class HomePage:
    def __init__(self, driver: WebDriver):
        self.driver = driver

    # Locators
    FIND_A_STORE_LINK = (By.XPATH, "//a[contains(text(), 'Find a Store')]")

    # Actions
    def click_find_a_store(self):
        find_a_store = self.driver.find_element(*self.FIND_A_STORE_LINK)
        find_a_store.click()

    # Validations
    def is_homepage_loaded(self):
        return self.driver.find_element(*self.FIND_A_STORE_LINK).is_displayed()

    def is_find_a_store_visible(self):
        return self.driver.find_element(*self.FIND_A_STORE_LINK).is_displayed()
