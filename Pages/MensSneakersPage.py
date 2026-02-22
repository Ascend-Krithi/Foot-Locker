import time
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException

class MensSneakersPage:
    URL = "https://www.footlocker.com/men/shoes/sneakers"
    HEADER = (By.XPATH, "//h1[contains(text(),'Men\'s Sneakers')]")
    FILTER_BUTTON = (By.ID, "filter-button")
    PRODUCT_LIST = (By.CSS_SELECTOR, ".product-list .product")

    def __init__(self, driver):
        self.driver = driver

    def access_mens_sneakers_page(self):
        self.driver.get(self.URL)
        return self.verify_mens_sneakers_page()

    def verify_mens_sneakers_page(self):
        try:
            WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located(self.HEADER)
            )
            return True
        except TimeoutException:
            return False

    def apply_filter(self):
        filter_btn = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.FILTER_BUTTON)
        )
        filter_btn.click()
        # Wait for product list to update
        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_any_elements_located(self.PRODUCT_LIST)
        )
        return True
