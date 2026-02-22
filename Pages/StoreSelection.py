from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreSelection:
    SET_MY_STORE_BUTTON = (By.XPATH, "//div[contains(text(), '375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class, 'store-card')]//button[contains(text(), 'Set My Store')]")

    def __init__(self, driver, timeout=10):
        self.driver = driver
        self.wait = WebDriverWait(driver, timeout)

    def click_set_my_store(self):
        self.wait.until(EC.element_to_be_clickable(self.SET_MY_STORE_BUTTON)).click()
