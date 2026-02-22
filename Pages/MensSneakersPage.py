# imports
from selenium.webdriver.common.by import By

class MensSneakersPage:
    def __init__(self, driver):
        self.driver = driver

    def launch_page(self, url):
        self.driver.get(url)

    def verify_store_indicator(self, store_name):
        indicator = self.driver.find_element(By.CSS_SELECTOR, ".store-indicator")
        return store_name in indicator.text
