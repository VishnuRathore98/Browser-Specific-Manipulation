import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InstallingWebExtensionTest {

    WebDriver driver;

    @BeforeAll
    void setup() throws URISyntaxException {
        Path extension = Paths.get(ClassLoader.getSystemResource("dark-bg.crx").toURI());
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(extension.toFile());
        driver = WebDriverManager.chromedriver().capabilities(options).create();
    }

    @Test
    void test() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java");
        Thread.sleep(Duration.ofSeconds(60));
    }

}
