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

    # --- New methods appended for test case coverage ---
    def verify_find_store_popup_message(self, expected_message):
        """
        Verifies that the popup message matches the expected text.
        Args:
            expected_message (str): The message expected in the popup.
        Returns:
            bool: True if the expected message is found, False otherwise.
        """
        try:
            # Assuming the popup message has a unique selector; adjust if needed
            popup = self.driver.find_element(By.XPATH, "//div[contains(@class, 'store-popup') or contains(@class, 'modal')]")
            return expected_message in popup.text
        except NoSuchElementException:
            return False

    def verify_select_my_store_link_visible(self):
        """
        Verifies that the 'Select My Store' link/button is visible within the popup.
        Returns:
            bool: True if the link/button is visible, False otherwise.
        """
        try:
            element = self.driver.find_element(By.XPATH, self.locators['select_my_store_button']['value'])
            return element.is_displayed()
        except NoSuchElementException:
            return False

    def verify_location_textbox_and_search_button_present(self):
        """
        Verifies the presence of the 'Location' textbox and 'Search for Stores' button in the popup.
        Returns:
            bool: True if both elements are present and visible, False otherwise.
        """
        try:
            location_box = self.driver.find_element(By.ID, self.locators['location_textbox']['value'])
            search_button = self.driver.find_element(By.XPATH, self.locators['search_for_stores_button']['value'])
            return location_box.is_displayed() and search_button.is_displayed()
        except NoSuchElementException:
            return False

    def is_store_address_present_in_results(self, address):
        """
        Checks if a store with the given address is present in the search results.
        Args:
            address (str): The address to search for.
        Returns:
            bool: True if the address is found in the results, False otherwise.
        """
        try:
            # Adjust the XPath if necessary to match the results structure
            store_elements = self.driver.find_elements(By.XPATH, f"//div[contains(@class, 'store-address') and contains(text(), '{address}')]")
            return any(address in elem.text for elem in store_elements)
        except NoSuchElementException:
            return False

    def verify_store_address_exact_match(self, expected_address):
        """
        Verifies that a store address in the results matches exactly the expected address.
        Args:
            expected_address (str): The address to match.
        Returns:
            bool: True if an exact match is found, False otherwise.
        """
        try:
            store_elements = self.driver.find_elements(By.XPATH, "//div[contains(@class, 'store-address')]")
            for elem in store_elements:
                if elem.text.strip() == expected_address.strip():
                    return True
            return False
        except NoSuchElementException:
            return False
