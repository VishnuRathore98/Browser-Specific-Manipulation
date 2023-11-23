import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HeadlessFirefoxJupiterTest {
    WebDriver driver;

    @BeforeEach
    void setup(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless=new");

        driver = WebDriverManager.firefoxdriver().capabilities(options).create();
    }
    @Test
    void test(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).containsIgnoringCase("selenium webdriver");
    }
}
