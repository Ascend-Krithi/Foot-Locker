# Executive Summary
# ConfirmationPage handles the validation of store selection confirmation and persistence of preferred store.
# This class is generated to support test steps verifying confirmation after store selection.

from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class ConfirmationPage:
    """
    PageClass for confirmation popup/indicator after setting preferred store.
    Implements store confirmation checks and preferred store persistence validation.
    """
    def __init__(self, driver):
        self.driver = driver
        self.confirmation_indicator_locator = (By.XPATH, "//div[contains(@class, 'confirmation') and contains(text(), 'Your store has been set')]")
        self.my_store_indicator_locator = (By.XPATH, "//span[contains(@class, 'my-store') and contains(text(), '375 Washington Street')]")

    def is_confirmation_visible(self):
        """Validate that confirmation indicator is visible after store selection."""
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.confirmation_indicator_locator)
        )

    def is_my_store_indicator_visible(self):
        """Validate that preferred store indicator is visible and persists across pages."""
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.my_store_indicator_locator)
        )

# Implementation Guide:
# - Use is_confirmation_visible() after store selection to assert confirmation.
# - Use is_my_store_indicator_visible() on any page to assert preferred store persistence.
# QA Report:
# - Locators validated against Locators.json.
# Troubleshooting:
# - If elements not found, check for dynamic loading or locator changes.
# Future Considerations:
# - Extend for dynamic store address or additional confirmation scenarios.
