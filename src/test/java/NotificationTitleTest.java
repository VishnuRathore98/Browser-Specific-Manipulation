import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NotificationTitleTest {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeAll
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications",1);
        options.setExperimentalOption("prefs",prefs);

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }
    @Test
    public void test(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/notifications.html");
        String script = String.join("\n",
   "const callback = arguments[arguments.length - 1];",
            "const OldNotify = window.Notification;",
            "function newNotification(title,options){",
            "callback(title);",
            "return new OldNotify(title,options);",
            "}",
            "newNotification.requestPermission = OldNotify.requestPermission.bind(OldNotify);",
            "Object.defineProperty(newNotification,'permission',{",
            "get : function(){",
            "return OldNotify.permission;",
            "}",
            "});",
            "window.Notification = newNotification;",
            "document.getElementById('notify-me').click();");

        Object notificationTitle = js.executeAsyncScript(script);
        System.out.println(notificationTitle.toString());
    }
    @AfterAll
    public void teardown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
