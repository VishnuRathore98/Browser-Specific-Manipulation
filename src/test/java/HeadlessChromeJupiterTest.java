import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HeadlessChromeJupiterTest {
    WebDriver driver;

    @BeforeAll
    void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
    }
    @Test
    void test(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).containsIgnoringCase("selenium webdriver");
    }
}
