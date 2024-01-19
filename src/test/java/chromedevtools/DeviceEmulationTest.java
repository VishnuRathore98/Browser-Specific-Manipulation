package chromedevtools;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.network.Network;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeviceEmulationTest {
    WebDriver driver;
    DevTools devTools;
    String userAgent;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
    }

    @Test
    public void test(){
    userAgent="Mozilla/5.0 (iphone; CPU iphone OS 8_0 like Mac OS X) " +
              "AppleWebKit/600.1.3 (KHTML, like Gecko)" +
              "Version/8.0 Mobile/12A4345d Safari/600.1.4";

//      We override the user agent for emulating an Apple iPhone 6.
        devTools.send(Network.setUserAgentOverride(userAgent, Optional.empty(), Optional.empty(), Optional.empty()));

        Map<String, Object> deviceMatrics = new HashMap<>();
        deviceMatrics.put("width",375);
        deviceMatrics.put("height",667);
        deviceMatrics.put("mobile",true);
        deviceMatrics.put("deviceScaleFactor",2);

//      We override the device screen parameters.
        ((ChromeDriver)driver).executeCdpCommand("Emulation.setDeviceMetricsOverride",deviceMatrics);

        driver.get("https://bonigarcia.dev/selenium-webdriver-java");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");

    }

    @AfterAll
    public void teardown()throws InterruptedException{
//        Thread.sleep(3000);
//        driver.quit();
    }
}
