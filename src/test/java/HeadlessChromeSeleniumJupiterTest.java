import io.github.bonigarcia.seljup.Arguments;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HeadlessChromeSeleniumJupiterTest {

    @Test
    void test(@Arguments("--headless")ChromeDriver driver){
            driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
            assertThat(driver.getTitle()).containsIgnoringCase("selenium webdriver");
    }
}
