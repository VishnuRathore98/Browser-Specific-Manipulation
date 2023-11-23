import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HeadlessEdgeJupiterTest {
    WebDriver driver;

    @BeforeAll
    void setupClass(){
        WebDriverManager.edgedriver().setup();
    }
    @BeforeEach
    void setup(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");

        driver = RemoteWebDriver.builder().oneOf(options).build();
    }
    @Test
    void test(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).containsIgnoringCase("selenium webdriver");
    }
}
