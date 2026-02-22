package com.footlocker.tests;

import com.footlocker.core.BaseTest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseStoreLocatorTest extends BaseTest {
    protected String baseUrl;

    public BaseStoreLocatorTest() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/test/resources/config.properties"));
            baseUrl = props.getProperty("baseUrl");
        } catch (IOException e) {
            baseUrl = "https://www.footlocker.com/store-locator";
        }
    }
}