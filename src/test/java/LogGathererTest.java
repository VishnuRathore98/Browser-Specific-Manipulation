import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType.*;

import java.util.logging.Level;

import static org.openqa.selenium.chrome.ChromeOptions.LOGGING_PREFS;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogGathererTest {
    WebDriver driver;
    ChromeOptions options;
    LoggingPreferences logs;
    LogEntries browserLogs;

    @BeforeAll
    public void setup(){
        logs = new LoggingPreferences();

//      Here, we enable gathering all levels of browser logs.
        logs.enable(LogType.BROWSER, Level.ALL);

        options = new ChromeOptions();

//      We set the loggingPrefs capability.
        options.setCapability(LOGGING_PREFS, logs);

        driver = new ChromeDriver(options);
    }

    @Test
    public void test(){
//      We open a practice page that logs several traces in the browser console.
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/console-logs.html");

//      We gather all the logs and filter them by browser(console traces).
        browserLogs = driver.manage().logs().get(LogType.BROWSER);

//      We verify the number of traces is not zero.
        Assertions.assertThat(browserLogs.getAll()).isNotEmpty();

//      We display each log in the standard output.

//      browserLogs.forEach(l -> System.out.println(l)); // Using lambda expression explicitly.
//      browserLogs.forEach(System.out::println); // Using lambda expression implicitly.

        for (LogEntry l : browserLogs) {
            System.out.println(l);
        }
    }

    @AfterAll
    public void teardown() throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();
    }
}
