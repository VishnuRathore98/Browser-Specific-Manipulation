import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MobileEmulationByAttributesTest {

    WebDriver driver;

    @BeforeAll
    void setup() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        Map<String,Object> mobileEmulation = new HashMap<>();
        Map<String,Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width",360);
        deviceMetrics.put("height",640);
        deviceMetrics.put("pixelRatio",3.0);
        deviceMetrics.put("touch",true);
        mobileEmulation.put("deviceMetrics",deviceMetrics);
        mobileEmulation.put("userAgent","Mozila/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D)"
                            +"AppleWebKit/535.19 (KHTML, like Gecko)"
                            +"Chrome/18.0.1025.166 Mobile Safari/535.19");
        options.setExperimentalOption("mobileEmulation",mobileEmulation);
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        Thread.sleep(Duration.ofSeconds(10));

    }

    @Test
    void test() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java");
        Thread.sleep(Duration.ofSeconds(10));
    }

}
