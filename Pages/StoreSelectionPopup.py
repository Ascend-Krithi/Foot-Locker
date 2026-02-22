from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreSelectionPopup:
    def __init__(self, driver):
        self.driver = driver
        self.location_textbox_locator = (By.ID, "store-locator-search-input")
        self.search_button_locator = (By.XPATH, "//button[contains(text(), 'Search for Stores')]")
        self.set_my_store_button_locator = (By.XPATH, "//div[contains(text(), '375 Washington Street, Boston, MA 02108')]/ancestor::div[contains(@class, 'store-card')]//button[contains(text(), 'Set My Store')]")

    def is_location_textbox_present(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.location_textbox_locator)
        )

    def is_search_button_present(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.search_button_locator)
        )

    def enter_location(self, location):
        textbox = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.location_textbox_locator)
        )
        textbox.clear()
        textbox.send_keys(location)

    def click_search_for_stores(self):
        search_btn = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.search_button_locator)
        )
        search_btn.click()

    def click_set_my_store(self):
        set_store_btn = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.set_my_store_button_locator)
        )
        set_store_btn.click()

    # Appended method
    def select_first_store(self):
        """Selects the first store from the list."""
        first_store = self.driver.find_element(By.XPATH, "//div[@class='store-item'][1]")
        first_store.click()
