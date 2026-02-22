from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreLocatorPopup:
    def __init__(self, driver):
        self.driver = driver
        self.popup_locator = (By.XPATH, "//div[@data-testid='store-locator-popup']") # Example: adjust as needed
        self.popup_message_locator = (By.XPATH, "//div[contains(text(),'Choose a preferred store to make shopping easier')]")
        self.select_my_store_button_locator = (By.XPATH, "//button[contains(text(), 'Select My Store')]")

    def is_popup_visible(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.popup_locator)
        )

    def get_popup_message(self):
        popup_message = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.popup_message_locator)
        )
        return popup_message.text

    def is_select_my_store_button_visible(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.select_my_store_button_locator)
        )

    def click_select_my_store_button(self):
        select_btn = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.select_my_store_button_locator)
        )
        select_btn.click()

    # Appended methods
    def enter_location(self, location):
        """Enters the location in the location input field."""
        location_input = self.driver.find_element(By.NAME, "location")
        location_input.clear()
        location_input.send_keys(location)

    def click_search(self):
        """Clicks the 'Search' button in the Store Locator popup."""
        search_btn = self.driver.find_element(By.CSS_SELECTOR, ".search-btn")
        search_btn.click()
