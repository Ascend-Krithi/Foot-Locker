from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver

class StoreSearchResults:
    SET_MY_STORE_BUTTON_XPATH = "//button[contains(text(), 'Set My Store') and ancestor::div[contains(., '{address}')]]"
    STORE_ADDRESS_DIV_XPATH = "//div[contains(text(), '{address}')]

    def __init__(self, driver: WebDriver):
        self.driver = driver

    def click_set_my_store_for_address(self, address: str):
        xpath = self.SET_MY_STORE_BUTTON_XPATH.format(address=address)
        set_store_btn = self.driver.find_element(By.XPATH, xpath)
        set_store_btn.click()

    def is_store_set_as_preferred(self, address: str):
        # This method should verify that the store is set as preferred, e.g. by checking a marker or text
        # Placeholder: Checks if the address div is present and marked as preferred
        address_div = self.driver.find_element(By.XPATH, self.STORE_ADDRESS_DIV_XPATH.format(address=address))
        return 'preferred' in address_div.get_attribute('class')

    def refresh_page(self):
        self.driver.refresh()
