import time
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException

class StoreLocatorPage:
    URL = "https://www.footlocker.com/store-locator"
    DESKTOP_WIDTH = 1920
    DESKTOP_HEIGHT = 1080
    TABLET_WIDTH = 768
    TABLET_HEIGHT = 1024
    MOBILE_WIDTH = 375
    MOBILE_HEIGHT = 667

    STORE_LOCATOR_HEADER = (By.XPATH, "//h1[contains(text(),'Store Locator')]")
    INTERACTIVE_ELEMENTS = (By.CSS_SELECTOR, "a, button, input, select, textarea")

    def __init__(self, driver):
        self.driver = driver

    def access_store_locator_page(self, device_type='desktop'):
        if device_type == 'desktop':
            self.driver.set_window_size(self.DESKTOP_WIDTH, self.DESKTOP_HEIGHT)
        elif device_type == 'tablet':
            self.driver.set_window_size(self.TABLET_WIDTH, self.TABLET_HEIGHT)
        elif device_type == 'mobile':
            self.driver.set_window_size(self.MOBILE_WIDTH, self.MOBILE_HEIGHT)
        else:
            raise ValueError("Invalid device type. Use 'desktop', 'tablet', or 'mobile'.")
        self.driver.get(self.URL)
        return self.verify_store_locator_page()

    def verify_store_locator_page(self):
        try:
            WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located(self.STORE_LOCATOR_HEADER)
            )
            return True
        except TimeoutException:
            return False

    def keyboard_navigation_accessibility(self):
        elements = self.driver.find_elements(*self.INTERACTIVE_ELEMENTS)
        for elem in elements:
            elem.send_keys(Keys.TAB)
            # Optionally check for focus state
            if not elem == self.driver.switch_to.active_element:
                return False
        return True

    def screen_reader_accessibility(self):
        # Placeholder for actual screen reader automation, but checks ARIA attributes and alt texts
        elements = self.driver.find_elements(*self.INTERACTIVE_ELEMENTS)
        for elem in elements:
            aria_label = elem.get_attribute('aria-label')
            alt_text = elem.get_attribute('alt')
            role = elem.get_attribute('role')
            if not (aria_label or alt_text or role):
                return False
        return True
