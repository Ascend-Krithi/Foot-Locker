# imports
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException

class StoreLocatorPage:
    def __init__(self, driver, locators):
        self.driver = driver
        self.locators = locators['StoreLocatorPage']

    def launch_homepage(self, url):
        self.driver.get(url)

    def click_find_store(self):
        self.driver.find_element(By.LINK_TEXT, self.locators['find_store_link']['value']).click()

    def click_select_my_store(self):
        self.driver.find_element(By.XPATH, self.locators['select_my_store_button']['value']).click()

    def enter_location(self, location):
        self.driver.find_element(By.ID, self.locators['location_textbox']['value']).send_keys(location)

    def click_search_for_stores(self):
        self.driver.find_element(By.XPATH, self.locators['search_for_stores_button']['value']).click()

    def click_set_my_store(self):
        self.driver.find_element(By.XPATH, self.locators['set_my_store_button']['value']).click()

    def verify_confirmation(self):
        return self.driver.find_element(By.CSS_SELECTOR, self.locators['confirmation_indicator']['value']).is_displayed()

    def is_find_store_popup_displayed(self):
        """
        Verifies if the 'Find a Store' popup is displayed below the button.
        Assumes the popup is anchored to the 'Select My Store' button.
        """
        try:
            element = self.driver.find_element(By.XPATH, self.locators['select_my_store_button']['value'])
            return element.is_displayed()
        except NoSuchElementException:
            return False

    def get_find_store_popup_message(self):
        """
        Returns the popup message text for the 'Find a Store' popup.
        Assumes the message is in a sibling or parent of the 'Select My Store' button.
        """
        try:
            # This XPath assumes the message is in a parent div of the button
            button = self.driver.find_element(By.XPATH, self.locators['select_my_store_button']['value'])
            popup_container = button.find_element(By.XPATH, "./ancestor::div[1]")
            return popup_container.text
        except NoSuchElementException:
            return ""

    def is_select_my_store_link_visible(self):
        """
        Verifies the presence of the 'Select My Store' link/button within the popup.
        """
        try:
            element = self.driver.find_element(By.XPATH, self.locators['select_my_store_button']['value'])
            return element.is_displayed()
        except NoSuchElementException:
            return False

    def is_location_textbox_present(self):
        """
        Verifies the presence of the 'Location' textbox in the store locator popup.
        """
        try:
            element = self.driver.find_element(By.ID, self.locators['location_textbox']['value'])
            return element.is_displayed()
        except NoSuchElementException:
            return False

    def is_search_for_stores_button_present(self):
        """
        Verifies the presence of the 'Search for Stores' button in the popup.
        """
        try:
            element = self.driver.find_element(By.XPATH, self.locators['search_for_stores_button']['value'])
            return element.is_displayed()
        except NoSuchElementException:
            return False
