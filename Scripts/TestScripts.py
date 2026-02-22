# Auto-generated TestScripts.py
import unittest
from selenium import webdriver

class FootLockerTestCase(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)

    def tearDown(self):
        self.driver.quit()

    def test_scrum_15408_ts_005_tc_001(self):
        driver = self.driver
        # Step 1: Launch homepage
        driver.get('https://www.footlocker.com')
        # Step 2: Click 'Find a Store' then 'Select My Store'
        # (Assumed selectors; replace with real ones when Locators are available)
        driver.find_element('link text', 'Find a Store').click()
        driver.find_element('link text', 'Select My Store').click()
        # Step 3: Enter 'Boston, MA' in 'Location' textbox
        driver.find_element('id', 'store-locator-location').send_keys('Boston, MA')
        # Step 4: Click 'Search for Stores'
        driver.find_element('id', 'search-for-stores').click()
        # Step 5: Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'
        # (Assume we can locate by address text for now)
        driver.find_element('xpath', "//div[contains(text(), '375 Washington Street')]/following-sibling::button[contains(text(), 'Set My Store')]").click()
        # Step 6: Verify confirmation indicator
        confirmation = driver.find_element('id', 'store-set-confirmation')
        assert confirmation.is_displayed()

    def test_scrum_15408_ts_006_tc_001(self):
        driver = self.driver
        # Step 1: Launch homepage
        driver.get('https://www.footlocker.com')
        # Step 2: Click 'Find a Store' then 'Select My Store'
        driver.find_element('link text', 'Find a Store').click()
        driver.find_element('link text', 'Select My Store').click()
        # Step 3: Enter 'Boston, MA' in 'Location' textbox
        driver.find_element('id', 'store-locator-location').send_keys('Boston, MA')
        # Step 4: Click 'Search for Stores'
        driver.find_element('id', 'search-for-stores').click()
        # Step 5: Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'
        driver.find_element('xpath', "//div[contains(text(), '375 Washington Street')]/following-sibling::button[contains(text(), 'Set My Store')]").click()
        # Step 6: Navigate to Men's Sneakers
        driver.get('https://www.footlocker.com/men/shoes')
        # Step 7: Verify store persists
        store_indicator = driver.find_element('id', 'preferred-store-indicator')
        assert '375 Washington Street' in store_indicator.text

if __name__ == '__main__':
    unittest.main()
