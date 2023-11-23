import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MobileEmulationByDeviceTest {

    WebDriver driver;

    @BeforeAll
    void setup() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName","iPhone XR");
        options.setExperimentalOption("mobileEmulation",mobileEmulation);
        driver = new ChromeDriver(options);
//        driver = WebDriverManager.chromedriver().capabilities(options).create();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

    }

    @Test
    void test(){
    }
}
