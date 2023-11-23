import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

import org.slf4j.Logger;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PageLoadChromeJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;
    PageLoadStrategy pageLoadStrategy;

    @BeforeAll
    void setup(){
        ChromeOptions options = new ChromeOptions();
        pageLoadStrategy = PageLoadStrategy.NORMAL;
        options.setPageLoadStrategy(pageLoadStrategy);

        driver = WebDriverManager.chromedriver().capabilities(options).create();
    }
    @Test
    void test(){
        long initMillis = System.currentTimeMillis();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        Duration elapsed = Duration.ofMillis(System.currentTimeMillis() - initMillis);
        Capabilities capabilities = ((RemoteWebDriver)driver).getCapabilities();
        Object pageLoad = capabilities.getCapability(CapabilityType.PAGE_LOAD_STRATEGY);
        String browserName = capabilities.getBrowserName();

        log.debug("The page took {} ms to be loaded using a {} strategy in {}", elapsed.toMillis() ,pageLoad, browserName);
        System.out.println("The page took " +elapsed.toMillis()+ " ms to be loaded using a " +pageLoad+ " strategy in "+ browserName);

        assertThat(pageLoad).isEqualTo(pageLoadStrategy.toString());
    }
    @AfterAll
    void teardown(){
//        driver.quit();
    }
}
