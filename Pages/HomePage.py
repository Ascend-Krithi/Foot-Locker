# imports
import json
import os
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class HomePage:
    def __init__(self, driver):
        self.driver = driver
        with open(os.path.join(os.path.dirname(__file__), '../Locators/Locators.json')) as f:
            self.locators = json.load(f)

    def load_homepage(self, url='https://www.footlocker.com/'):
        self.driver.get(url)
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((
                getattr(By, self.locators['homepage_find_store_link']['by'].upper()),
                self.locators['homepage_find_store_link']['value']
            ))
        )
        return True

    def click_find_a_store(self):
        find_store = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((
                getattr(By, self.locators['homepage_find_store_link']['by'].upper()),
                self.locators['homepage_find_store_link']['value']
            ))
        )
        find_store.click()
        return True
