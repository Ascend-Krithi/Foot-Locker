from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class Confirmation:
    CONFIRMATION_INDICATOR = (By.XPATH, "//div[contains(@class, 'confirmation') and contains(text(), 'Your store has been set')]")
    MY_STORE_INDICATOR = (By.XPATH, "//span[contains(@class, 'my-store') and contains(text(), '375 Washington Street')]")

    def __init__(self, driver, timeout=10):
        self.driver = driver
        self.wait = WebDriverWait(driver, timeout)

    def is_store_set_confirmation_displayed(self):
        return self.wait.until(EC.visibility_of_element_located(self.CONFIRMATION_INDICATOR))

    def is_my_store_indicator_displayed(self):
        return self.wait.until(EC.visibility_of_element_located(self.MY_STORE_INDICATOR))
