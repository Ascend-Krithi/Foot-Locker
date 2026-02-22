import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogoutTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://example.com/dashboard");
        // Assume user is already logged in for this test
    }

    @Test
    public void testLogout() {
        driver.findElement(By.id("logoutButton")).click();
        WebElement loginPage = driver.findElement(By.id("loginPage"));
        Assert.assertTrue(loginPage.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
