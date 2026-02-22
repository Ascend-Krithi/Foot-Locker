# HomePage.py
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class HomePage:
    FIND_A_STORE_LINK = (By.LINK_TEXT, "Find a Store")

    def __init__(self, driver: WebDriver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 10)

    def go_to_homepage(self, url: str):
        self.driver.get(url)
        self.wait.until(EC.presence_of_element_located(self.FIND_A_STORE_LINK))

    def click_find_a_store(self):
        find_store_link = self.wait.until(EC.element_to_be_clickable(self.FIND_A_STORE_LINK))
        find_store_link.click()
