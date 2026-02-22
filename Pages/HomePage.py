from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class HomePage:
    def __init__(self, driver):
        self.driver = driver
        self.url = 'https://www.footlocker.com/'
        self.find_a_store_locator = (By.XPATH, "//a[contains(text(), 'Find a Store')]")
        self.my_store_indicator_locator = (By.XPATH, "//span[contains(@class, 'my-store') and contains(text(), '375 Washington Street')]")

    def load_homepage(self):
        self.driver.get(self.url)
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(self.find_a_store_locator)
        )

    def click_find_a_store(self):
        find_store_btn = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.find_a_store_locator)
        )
        find_store_btn.click()

    def is_my_store_indicator_visible(self):
        """Validate that preferred store indicator is visible on homepage."""
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.my_store_indicator_locator)
        )

# Executive Summary:
# HomePage supports homepage loading, store locator access, and store indicator validation.
# Implementation Guide:
# - Use load_homepage() to load the homepage.
# - Use click_find_a_store() to open store locator popup.
# - Use is_my_store_indicator_visible() to validate preferred store.
# QA Report:
# - Locators validated against Locators.json.
# Troubleshooting:
# - If homepage fails, check URL or locator changes.
# Future Considerations:
# - Extend for additional homepage elements or flows.
