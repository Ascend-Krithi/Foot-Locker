from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class ConfirmationPage:
    def __init__(self, driver):
        self.driver = driver
        self.confirmation_indicator_locator = (By.XPATH, "//div[contains(@class, 'confirmation') and contains(text(), 'Your store has been set')]")

    def is_store_set_confirmation_visible(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.confirmation_indicator_locator)
        )
