from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreSelectionPopup:
    def __init__(self, driver):
        self.driver = driver
        self.location_textbox_locator = (By.ID, "store-locator-search-input")
        self.search_button_locator = (By.XPATH, "//button[contains(text(), 'Search for Stores')]")

    def is_location_textbox_present(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.location_textbox_locator)
        )

    def is_search_button_present(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.search_button_locator)
        )
