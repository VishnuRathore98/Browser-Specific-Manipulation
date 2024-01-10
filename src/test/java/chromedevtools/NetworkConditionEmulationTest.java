package chromedevtools;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.network.Network;
import org.openqa.selenium.devtools.v118.network.model.ConnectionType;

import java.time.Duration;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NetworkConditionEmulationTest {
    WebDriver driver;
    DevTools devTools;
    long initMillis;
    Duration elapsed;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
    }
    @Test
    public void test(){
//      We activate network tracking(Without tuning any network parameter).
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

//      We emulate a mobile 3g network with 50 KBps as download and upload bandwidth.
        devTools.send(Network.emulateNetworkConditions(false,100,50*1024,50*1024,Optional.of(ConnectionType.CELLULAR3G)));

//      We get a system timestamp before loading a web page.
        initMillis = System.currentTimeMillis();

//      We load the index page of the practice site.
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

//      We calculate the required time to load this page.
        elapsed = Duration.ofMillis(System.currentTimeMillis() - initMillis);
        System.out.println("The page took "+elapsed.toMillis()+" ms to be loaded.");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
    @AfterEach
    public void teardown()throws InterruptedException{
        Thread.sleep(3000);
        devTools.close();
        driver.quit();
    }
}
