# Existing imports and code remain unchanged
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import pytest
from Pages.HomePage import HomePage
from Pages.StoreLocatorPopup import StoreLocatorPopup

# Existing test classes and methods...

# --- Existing content above ---

# SCRUM-15408 TS-005 TC-002
@pytest.mark.sprint('SCRUM-15408')
def test_TC_002_select_preferred_store_and_verify_in_header(driver):
    """
    Steps:
    1. Launch homepage
    2. Click 'Find a Store'
    3. Click 'Select My Store'
    4. Enter 'Boston, MA'
    5. Search
    6. Set preferred store to '375 Washington Street, Boston, MA 02108'
    7. Verify the selected store appears in the header/location indicator across the website
    """
    home_page = HomePage(driver)
    store_popup = StoreLocatorPopup(driver)

    home_page.load()
    home_page.click_find_a_store()
    store_popup.click_select_my_store()
    store_popup.enter_location('Boston, MA')
    store_popup.click_search()
    store_popup.set_preferred_store('375 Washington Street, Boston, MA 02108')
    
    # Verify preferred store in header
    assert home_page.get_selected_store() == '375 Washington Street, Boston, MA 02108', \
        "Preferred store not displayed in header/location indicator."

# SCRUM-15408 TS-006 TC-001
@pytest.mark.sprint('SCRUM-15408')
def test_TC_001_preferred_store_persists_across_navigation(driver):
    """
    Steps:
    1. Launch homepage
    2. Click 'Find a Store'
    3. Click 'Select My Store'
    4. Enter 'Boston, MA'
    5. Search
    6. Set preferred store to '375 Washington Street, Boston, MA 02108'
    7. Navigate to Women's Shoes page
    8. Verify preferred store remains set
    """
    home_page = HomePage(driver)
    store_popup = StoreLocatorPopup(driver)

    home_page.load()
    home_page.click_find_a_store()
    store_popup.click_select_my_store()
    store_popup.enter_location('Boston, MA')
    store_popup.click_search()
    store_popup.set_preferred_store('375 Washington Street, Boston, MA 02108')
    
    # Navigate to Women's Shoes page
    home_page.go_to_womens_shoes()

    # Verify preferred store persists
    assert home_page.get_selected_store() == '375 Washington Street, Boston, MA 02108', \
        "Preferred store did not persist after navigation."

# --- End of file ---
