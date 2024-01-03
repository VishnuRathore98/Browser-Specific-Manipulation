import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChromeNotificationsTest {
WebDriver driver;
    ChromeOptions options;
    Map<String,Object> prefs;
    @BeforeAll
    void setup() throws InterruptedException {
        options = new ChromeOptions();
        prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_value.notifications",1);
        options.setExperimentalOption("prefs",prefs);

    }
    @Test
    void test() throws InterruptedException {
        WebDriverManager.chromedriver().capabilities(options).create().get("https://bonigarcia.dev/selenium-webdriver-java/notifications.html");

        Thread.sleep(Duration.ofSeconds(20));
    }
    @AfterAll
    void teardown(){

    }
}