from selenium.webdriver.common.by import By

class HomePage:
    FIND_A_STORE_LINK = (By.XPATH, "//a[contains(text(), 'Find a Store')]")
    HOME_LOGO = (By.CSS_SELECTOR, "a.header-logo")

    def __init__(self, driver):
        self.driver = driver

    def click_find_a_store(self):
        self.driver.find_element(*self.FIND_A_STORE_LINK).click()

    def click_home_logo(self):
        self.driver.find_element(*self.HOME_LOGO).click()
