import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NotificationsTest {

    WebDriver driver;

    @BeforeAll
    public void setup(){

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications",1);
        options.setExperimentalOption("prefs",prefs);

        driver = new ChromeDriver(options);

//        driver = WebDriverManager.chromedriver().capabilities(options).create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void test() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/notifications.html");
        driver.findElement(By.id("notify-me")).click();
    }
    @AfterAll
    public void teardown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
