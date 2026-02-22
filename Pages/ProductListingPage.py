# ProductListingPage.py
"""
PageClass for product listing pages, supporting navigation and store indicator validation.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class ProductListingPage:
    def __init__(self, driver):
        self.driver = driver
        self.home_logo_locator = (By.CSS_SELECTOR, "a.header-logo")
        self.my_store_indicator_locator = (By.XPATH, "//span[contains(@class, 'my-store') and contains(text(), '375 Washington Street')]")

    def navigate_to_product_listing(self):
        self.driver.get("https://www.footlocker.com/category/mens/shoes.html")
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(self.home_logo_locator)
        )

    def is_my_store_indicator_visible(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.my_store_indicator_locator)
        )
