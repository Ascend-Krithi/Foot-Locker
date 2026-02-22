from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class StoreLocatorPopup:
    def __init__(self, driver):
        self.driver = driver
        self.popup_locator = (By.XPATH, "//div[@data-testid='store-locator-popup']") # Example: adjust as needed
        self.popup_message_locator = (By.XPATH, "//div[contains(text(),'Choose a preferred store to make shopping easier')]")
        self.select_my_store_link_locator = (By.XPATH, "//a[contains(text(), 'Select My Store')]")

    def is_popup_visible(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.popup_locator)
        )

    def get_popup_message(self):
        popup_message = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.popup_message_locator)
        )
        return popup_message.text

    def is_select_my_store_link_visible(self):
        return WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.select_my_store_link_locator)
        )

    def click_select_my_store_link(self):
        select_link = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.select_my_store_link_locator)
        )
        select_link.click()
