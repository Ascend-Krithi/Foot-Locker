from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class ProductListingPage:
    def __init__(self, driver):
        self.driver = driver
        self.url = 'https://www.footlocker.com/category/mens.html' # Example URL, adjust as needed
        self.my_store_indicator_locator = (By.XPATH, "//span[contains(@class, 'my-store') and contains(text(), '375 Washington Street')]")
        self.home_logo_locator = (By.CSS_SELECTOR, "a.header-logo")

    def load_page(self):
        self.driver.get(self.url)
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(self.home_logo_locator)
        )

    def is_my_store_indicator_visible(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.my_store_indicator_locator)
        )
