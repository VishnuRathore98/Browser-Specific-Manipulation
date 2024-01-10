package chromedevtools;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DevToolsSetupTest {
    WebDriver driver;

//  Declaring DevTools class attribute.
    DevTools devTools;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();

//      We get the DevTools instance from the driver object.EdgeDriver instance would also be valid.
        devTools = ((ChromeDriver)driver).getDevTools();

//      We create a CDP session to interact with Chrome DevTools in the test logic.
        devTools.createSession();
    }
    @AfterEach
    public void teardown(){
//      After each test and before quitting the WebDriver session, we terminate the CDP session.
        devTools.close();
        driver.quit();
    }
}
