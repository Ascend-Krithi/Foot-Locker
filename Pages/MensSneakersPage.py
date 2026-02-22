# imports
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
class MensSneakersPage:
    def __init__(self, driver, timeout=10):
        self.driver = driver
        self.wait = WebDriverWait(driver, timeout)
    def launch_page(self, url):
        self.driver.get(url)
    def verify_store_indicator(self, store_name):
        indicator = self.wait.until(EC.visibility_of_element_located((By.CSS_SELECTOR, ".store-indicator")))
        return store_name in indicator.text
    # Appended for SCRUM-15408 TS-001 TC-010/011
    def is_preferred_store_set(self, store_address="375 Washington Street, Boston, MA 02108"):
        try:
            indicator = self.wait.until(EC.visibility_of_element_located((By.CSS_SELECTOR, ".store-indicator")))
            return store_address in indicator.text
        except Exception:
            return False
