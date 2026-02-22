# Placeholder TestScripts.py

# TODO: Add test methods for testCaseId 2015, 2016, etc.

def test_case_2017():
    ... # (existing methods omitted for brevity)

def test_case_2101(driver):
    ... # (existing methods omitted for brevity)

def test_case_2103(driver):
    """
    Test Case 2103 (SCRUM-15408 TS-SL-001 TC-001):
    Launch homepage, verify 'Find a Store' link is visible, click the link, verify Store Locator popup.
    """
    from Pages.HomepagePage import HomepagePage
    homepage = HomepagePage(driver)
    homepage.launch_homepage()
    assert homepage.is_find_a_store_link_visible(), "'Find a Store' link should be visible on homepage."
    homepage.click_find_a_store_link()
    assert homepage.is_store_locator_popup_visible(), "Store Locator popup should be visible after clicking 'Find a Store' link."

def test_case_2104(driver):
    """
    Test Case 2104 (SCRUM-15408 TS-SL-001 TC-002):
    Launch homepage, click 'Find a Store', verify popup message and 'Select My Store' link.
    """
    from Pages.HomepagePage import HomepagePage
    homepage = HomepagePage(driver)
    homepage.launch_homepage()
    homepage.click_find_a_store_link()
    assert homepage.is_store_locator_popup_visible(), "Store Locator popup should be visible after clicking 'Find a Store' link."
    popup_message = homepage.get_store_locator_popup_message()
    assert popup_message is not None and popup_message != "", "Popup message should be present in Store Locator popup."
    assert homepage.is_select_my_store_link_visible(), "'Select My Store' link should be visible in Store Locator popup."
