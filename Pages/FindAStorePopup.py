from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver

class FindAStorePopup:
    SELECT_MY_STORE_LINK = (By.LINK_TEXT, 'Select My Store')
    LOCATION_TEXTBOX = (By.ID, 'store-search-location')
    SEARCH_BUTTON = (By.XPATH, "//button[contains(text(), 'Search for Stores')]")
    POPUP_CONTAINER = (By.ID, 'find-store-popup')

    def __init__(self, driver: WebDriver):
        self.driver = driver

    def is_popup_displayed(self):
        return self.driver.find_element(*self.POPUP_CONTAINER).is_displayed()

    def click_select_my_store(self):
        select_my_store = self.driver.find_element(*self.SELECT_MY_STORE_LINK)
        select_my_store.click()

    def enter_location(self, location: str):
        location_box = self.driver.find_element(*self.LOCATION_TEXTBOX)
        location_box.clear()
        location_box.send_keys(location)

    def click_search_for_stores(self):
        search_btn = self.driver.find_element(*self.SEARCH_BUTTON)
        search_btn.click()
