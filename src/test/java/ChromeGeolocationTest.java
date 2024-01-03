import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChromeGeolocationTest {
    WebDriver driver;
    @BeforeAll
    void setup() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> pref = new HashMap<>();
        pref.put("profile.default_content_setting_value.geolocation",1);
        options.setExperimentalOption("prefs",pref);
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/geolocation.html");
        System.out.println("object: "+pref);
        System.out.println("value: "+pref.values());

        Thread.sleep(Duration.ofSeconds(4));
    }
    @Test
    void test(){}
}
