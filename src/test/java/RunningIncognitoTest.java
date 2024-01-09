import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RunningIncognitoTest {
    WebDriver driver;
    ChromeOptions options;

    @BeforeAll
    public void setup(){
        options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
    }

    @Test
    public void test(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
    }
    @AfterAll
    public void teardown() throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();
    }
}