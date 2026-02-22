# FindAStorePopup.py
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class FindAStorePopup:
    SELECT_MY_STORE_LINK = (By.LINK_TEXT, "Select My Store")
    LOCATION_TEXTBOX = (By.ID, "store-search-location")
    SEARCH_BUTTON = (By.XPATH, "//button[contains(text(), 'Search for Stores')]")
    POPUP_CONTAINER = (By.ID, "find-store-popup")

    def __init__(self, driver: WebDriver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 10)

    def is_displayed(self) -> bool:
        try:
            self.wait.until(EC.visibility_of_element_located(self.POPUP_CONTAINER))
            return True
        except:
            return False

    def click_select_my_store(self):
        select_my_store = self.wait.until(EC.element_to_be_clickable(self.SELECT_MY_STORE_LINK))
        select_my_store.click()

    def enter_location_and_search(self, location: str):
        location_box = self.wait.until(EC.visibility_of_element_located(self.LOCATION_TEXTBOX))
        location_box.clear()
        location_box.send_keys(location)
        search_btn = self.wait.until(EC.element_to_be_clickable(self.SEARCH_BUTTON))
        search_btn.click()
