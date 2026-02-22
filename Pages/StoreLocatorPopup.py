from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreLocatorPopup:
    SELECT_MY_STORE_BUTTON = (By.XPATH, "//button[contains(text(), 'Select My Store')]")
    LOCATION_TEXTBOX = (By.ID, "store-locator-search-input")
    SEARCH_FOR_STORES_BUTTON = (By.XPATH, "//button[contains(text(), 'Search for Stores')]")
    USE_MY_LOCATION_BUTTON = (By.XPATH, "//button[contains(text(), 'Use My Location')]")  # Assuming standard naming

    def __init__(self, driver, timeout=10):
        self.driver = driver
        self.wait = WebDriverWait(driver, timeout)

    def enter_location(self, location):
        textbox = self.wait.until(EC.visibility_of_element_located(self.LOCATION_TEXTBOX))
        textbox.clear()
        textbox.send_keys(location)

    def click_search_for_stores(self):
        self.wait.until(EC.element_to_be_clickable(self.SEARCH_FOR_STORES_BUTTON)).click()

    def click_use_my_location(self):
        self.wait.until(EC.element_to_be_clickable(self.USE_MY_LOCATION_BUTTON)).click()

    def click_select_my_store(self):
        self.wait.until(EC.element_to_be_clickable(self.SELECT_MY_STORE_BUTTON)).click()
