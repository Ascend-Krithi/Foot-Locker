# HomePage.py
"""
Page Object for the Foot Locker Homepage
Implements actions and validations for homepage elements
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver

class HomePage:
    def __init__(self, driver: WebDriver):
        self.driver = driver

    # Locators
    FIND_A_STORE_LINK = (By.XPATH, "//a[contains(text(), 'Find a Store')]")

    # Actions
    def click_find_a_store(self):
        find_a_store = self.driver.find_element(*self.FIND_A_STORE_LINK)
        find_a_store.click()

    # Validations
    def is_homepage_loaded(self):
        return self.driver.find_element(*self.FIND_A_STORE_LINK).is_displayed()

    def is_find_a_store_visible(self):
        return self.driver.find_element(*self.FIND_A_STORE_LINK).is_displayed()
