import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://example.com/login");
    }

    @Test
    public void testValidLogin() {
        driver.findElement(By.id("username")).sendKeys("validUser");
        driver.findElement(By.id("password")).sendKeys("validPass");
        driver.findElement(By.id("loginButton")).click();
        WebElement dashboard = driver.findElement(By.id("dashboard"));
        Assert.assertTrue(dashboard.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
