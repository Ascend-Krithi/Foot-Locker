# imports
from selenium.webdriver.common.by import By

class StoreLocatorPage:
    def __init__(self, driver, locators):
        self.driver = driver
        self.locators = locators['StoreLocatorPage']

    def launch_homepage(self, url):
        self.driver.get(url)

    def click_find_store(self):
        self.driver.find_element(By.LINK_TEXT, self.locators['find_store_link']['value']).click()

    def click_select_my_store(self):
        self.driver.find_element(By.XPATH, self.locators['select_my_store_button']['value']).click()

    def enter_location(self, location):
        self.driver.find_element(By.ID, self.locators['location_textbox']['value']).send_keys(location)

    def click_search_for_stores(self):
        self.driver.find_element(By.XPATH, self.locators['search_for_stores_button']['value']).click()

    def click_set_my_store(self):
        self.driver.find_element(By.XPATH, self.locators['set_my_store_button']['value']).click()

    def verify_confirmation(self):
        return self.driver.find_element(By.CSS_SELECTOR, self.locators['confirmation_indicator']['value']).is_displayed()
