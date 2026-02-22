# Scripts/TestScripts.py
import unittest
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup

class TestFootLocker(unittest.TestCase):
    """
    Test suite for Foot Locker website.
    """

    def setUp(self):
        """
        Sets up the Chrome driver for desktop by default.
        """
        chrome_options = Options()
        chrome_options.add_argument('--start-maximized')
        self.driver = webdriver.Chrome(options=chrome_options)
        self.driver.implicitly_wait(10)

    def tearDown(self):
        """
        Closes the browser and cleans up after each test.
        """
        self.driver.quit()

    # ...
    # Existing test methods are here, following the same structure and documentation style.
    # ...

    def test_store_locator_display_desktop_tablet_mobile(self):
        """
        TestCase 2113 (SCRUM-15408 TS-SL-010 TC-001)
        Verifies Store Locator page displays and functions correctly on desktop, tablet, and mobile browsers.
        Steps:
            1. Access the Store Locator page on a desktop browser. Expected: Store Locator page displays and functions correctly.
            2. Access the Store Locator page on a tablet browser. Expected: Store Locator page displays and functions correctly.
            3. Access the Store Locator page on a mobile browser. Expected: Store Locator page displays and functions correctly.
        """
        # --- Desktop ---
        desktop_options = Options()
        desktop_options.add_argument('--start-maximized')
        driver_desktop = webdriver.Chrome(options=desktop_options)
        driver_desktop.implicitly_wait(10)
        try:
            home_page = HomePage(driver_desktop)
            home_page.go_to_homepage()
            home_page.open_store_locator()
            store_locator = StoreLocatorPopup(driver_desktop)
            self.assertTrue(store_locator.is_displayed(), "Store Locator page should be displayed on desktop.")
            self.assertTrue(store_locator.is_functional(), "Store Locator page should function correctly on desktop.")
        finally:
            driver_desktop.quit()

        # --- Tablet ---
        tablet_options = Options()
        tablet_emulation = {
            "deviceMetrics": {"width": 800, "height": 1280, "pixelRatio": 2.0},
            "userAgent": "Mozilla/5.0 (Linux; Android 8.0.0; Tablet) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36"
        }
        tablet_options.add_experimental_option("mobileEmulation", tablet_emulation)
        driver_tablet = webdriver.Chrome(options=tablet_options)
        driver_tablet.implicitly_wait(10)
        try:
            home_page = HomePage(driver_tablet)
            home_page.go_to_homepage()
            home_page.open_store_locator()
            store_locator = StoreLocatorPopup(driver_tablet)
            self.assertTrue(store_locator.is_displayed(), "Store Locator page should be displayed on tablet.")
            self.assertTrue(store_locator.is_functional(), "Store Locator page should function correctly on tablet.")
        finally:
            driver_tablet.quit()

        # --- Mobile ---
        mobile_options = Options()
        mobile_emulation = {
            "deviceMetrics": {"width": 375, "height": 667, "pixelRatio": 2.0},
            "userAgent": "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1"
        }
        mobile_options.add_experimental_option("mobileEmulation", mobile_emulation)
        driver_mobile = webdriver.Chrome(options=mobile_options)
        driver_mobile.implicitly_wait(10)
        try:
            home_page = HomePage(driver_mobile)
            home_page.go_to_homepage()
            home_page.open_store_locator()
            store_locator = StoreLocatorPopup(driver_mobile)
            self.assertTrue(store_locator.is_displayed(), "Store Locator page should be displayed on mobile.")
            self.assertTrue(store_locator.is_functional(), "Store Locator page should function correctly on mobile.")
        finally:
            driver_mobile.quit()

    def test_store_locator_accessibility_keyboard_screenreader(self):
        """
        TestCase 2114 (SCRUM-15408 TS-SL-011 TC-001)
        Verifies Store Locator page accessibility: keyboard navigation and screen reader support.
        Steps:
            1. Navigate to the Store Locator page (URL: https://www.footlocker.com/store-locator). Expected: Store Locator page is displayed.
            2. Test keyboard navigation through all interactive elements. Expected: All elements are accessible via keyboard.
            3. Test with a screen reader. Expected: All elements are announced correctly and page is usable.
        """
        self.driver.get("https://www.footlocker.com/store-locator")
        store_locator = StoreLocatorPopup(self.driver)
        self.assertTrue(store_locator.is_displayed(), "Store Locator page should be displayed.")

        # Keyboard navigation test
        # Attempt to tab through interactive elements and verify focus changes
        interactive_selectors = store_locator.get_interactive_elements_selectors()
        for selector in interactive_selectors:
            element = self.driver.find_element(By.CSS_SELECTOR, selector)
            element.send_keys(Keys.TAB)
            # Check if the element is focused
            active = self.driver.switch_to.active_element
            self.assertEqual(element, active, f"Element {selector} should be focusable via keyboard.")

        # Note: Selenium cannot test screen reader output directly. For actual screen reader tests, integration with external tools (e.g., axe-core, NVDA, VoiceOver) is required.
        # The following is a placeholder for where such integration would occur.
        # Example: assert store_locator.all_elements_have_accessible_labels()
        # See accessibility test documentation for further steps.
        pass
