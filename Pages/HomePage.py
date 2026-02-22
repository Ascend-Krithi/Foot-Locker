# HomePage.py
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver

class HomePage:
    def __init__(self, driver: WebDriver):
        self.driver = driver

    def click_find_store_link(self):
        find_store_link = self.driver.find_element(By.XPATH, "//a[contains(@href, '/store-locator') and contains(text(), 'Find a Store')]")
        find_store_link.click()
