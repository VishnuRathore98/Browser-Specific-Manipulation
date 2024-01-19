package chromedevtools;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.network.Network;
import org.openqa.selenium.devtools.v118.network.model.BlockedReason;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BlockURLTest {
    WebDriver driver;
    DevTools devTools;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
    }

    @Test
    public void test(){
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        String urlToBlock = "https://bonigarcia.dev/selenium-webdriver-java/img/hands-on-icon.png";

//      We block a given URL.
        devTools.send(Network.setBlockedURLs(ImmutableList.of(urlToBlock)));

//      We create a listener to trace the failed events.
        devTools.addListener(Network.loadingFailed(),loadingFailed -> {
            BlockedReason reason = loadingFailed.getBlockedReason().get();
            System.out.println("Blocking reason: "+reason);
            assertThat(reason).isEqualTo(BlockedReason.INSPECTOR);
        });

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

    @AfterAll
    public void teardown() throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();
    }
}