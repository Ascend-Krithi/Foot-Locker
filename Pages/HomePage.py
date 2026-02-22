from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver

class HomePage:
    FIND_A_STORE_LINK = (By.LINK_TEXT, 'Find a Store')

    def __init__(self, driver: WebDriver):
        self.driver = driver

    def load(self, url: str):
        self.driver.get(url)

    def is_loaded(self):
        return 'footlocker.com' in self.driver.current_url

    def click_find_a_store(self):
        find_a_store = self.driver.find_element(*self.FIND_A_STORE_LINK)
        find_a_store.click()
