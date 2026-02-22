from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class HomePage:
    def __init__(self, driver):
        self.driver = driver
        self.url = 'https://www.footlocker.com/'
        self.find_a_store_locator = (By.XPATH, "//a[contains(text(), 'Find a Store')]")

    def load_homepage(self):
        self.driver.get(self.url)
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(self.find_a_store_locator)
        )

    def click_find_a_store(self):
        find_store_btn = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable(self.find_a_store_locator)
        )
        find_store_btn.click()
