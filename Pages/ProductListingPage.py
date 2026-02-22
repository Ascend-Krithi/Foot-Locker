# Executive Summary
# ProductListingPage enables navigation and validation for product listing pages.
# This class is generated to support test steps involving navigation away from homepage.

from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class ProductListingPage:
    """
    PageClass for product listing pages, supporting navigation and store indicator validation.
    """
    def __init__(self, driver):
        self.driver = driver
        self.home_logo_locator = (By.CSS_SELECTOR, "a.header-logo")
        self.my_store_indicator_locator = (By.XPATH, "//span[contains(@class, 'my-store') and contains(text(), '375 Washington Street')]")

    def navigate_to_product_listing(self):
        """Navigate to a sample product listing page."""
        self.driver.get("https://www.footlocker.com/category/mens/shoes.html")
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(self.home_logo_locator)
        )

    def is_my_store_indicator_visible(self):
        """Validate that preferred store indicator is visible on product listing page."""
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.my_store_indicator_locator)
        )

# Implementation Guide:
# - Use navigate_to_product_listing() to load product listing.
# - Use is_my_store_indicator_visible() to validate store persistence.
# QA Report:
# - Locators validated against Locators.json.
# Troubleshooting:
# - If product listing fails, check URL or site changes.
# Future Considerations:
# - Extend for cart or other navigation scenarios.
