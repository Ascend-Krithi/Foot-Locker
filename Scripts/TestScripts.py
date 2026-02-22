# Existing imports and code above...
import unittest
from selenium import webdriver
from StoreLocatorPage import StoreLocatorPage

class TestStoreLocatorPage(unittest.TestCase):
    # ...existing test methods...

    def test_TC_2113_store_locator_responsive(self):
        """
        TC-2113: Verify Store Locator page displays and functions correctly on desktop, tablet, and mobile devices.
        """
        device_viewports = {
            'desktop': {'width': 1920, 'height': 1080},
            'tablet': {'width': 768, 'height': 1024},
            'mobile': {'width': 375, 'height': 667}
        }
        for device, viewport in device_viewports.items():
            with self.subTest(device=device):
                self.driver.set_window_size(viewport['width'], viewport['height'])
                page = StoreLocatorPage(self.driver)
                self.assertTrue(page.is_displayed(), f"Store Locator page did not load on {device}.")

    def test_TC_2114_store_locator_accessibility(self):
        """
        TC-2114: Verify Store Locator page loads, supports keyboard navigation, and is accessible to screen readers.
        """
        page = StoreLocatorPage(self.driver)
        self.assertTrue(page.is_displayed(), "Store Locator page did not load.")
        self.assertTrue(page.all_elements_accessible_by_keyboard(), "Keyboard navigation accessibility failed.")
        self.assertTrue(page.all_elements_have_accessible_names(), "Screen reader accessibility failed.")

# Existing code below...
